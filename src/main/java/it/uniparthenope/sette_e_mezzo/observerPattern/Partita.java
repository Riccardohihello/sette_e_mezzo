package it.uniparthenope.sette_e_mezzo.observerPattern;

import it.uniparthenope.sette_e_mezzo.game.*;
import it.uniparthenope.sette_e_mezzo.memento.gameSettings;
import it.uniparthenope.sette_e_mezzo.strategyPattern.Giocatore;
import it.uniparthenope.sette_e_mezzo.strategyPattern.StrategiaComputer;
import it.uniparthenope.sette_e_mezzo.strategyPattern.StrategiaGiocatore;
import it.uniparthenope.sette_e_mezzo.strategyPattern.StrategiaMazziere;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Partita  {
    private final ArrayList<gameObserver> osservatori = new ArrayList<>();
    private int indiceScorrimento = 0;
    private final ArrayList<Giocatore> giocatori = new ArrayList<>();
    private Giocatore mazziere;
    private Giocatore computer;
    private final ArrayList<Integer> puntate = new ArrayList<>();

    public Partita() {
        giocatori.addAll(gameSettings.getSettings().getListaGiocatori());
        MazzoIteratorSingleton.getInstance().mischia();
        sceltaRuoli();
        getGiocatoreAttuale().setStato(Action.bid);
        notificaOsservatore(Action.bid);

        // gestisco le azioni del computer
        if(getGiocatoreAttuale().getNome().equals("Computer"))
            setQuota(getPiatto()/giocatori.size());
    }

    public int getPiatto(){ // somma del totale delle puntate
        int valorePiatto = 0;
        for (Integer puntata : puntate) {
            valorePiatto += puntata;
        }
        return valorePiatto;
    }

    private void notificaOsservatore(Action action, String... message) {
        // metodo per notificare gli osservatori
        if (!osservatori.isEmpty())
            for (gameObserver observer : this.osservatori)
                observer.update(action,message);
    }

    public void addObserver(gameObserver osservatore) {
        this.osservatori.add(osservatore);
    }

    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    private void sceltaRuoli() {
        Random random = new Random();
        int rand;

        do
            rand = random.nextInt(giocatori.size());
        while (giocatori.get(rand).getNome().equals("Computer"));
        Giocatore mazziere = giocatori.get(rand);   // sceglie la posizione del mazziere se non è il computer

        do
            Collections.shuffle(giocatori);     // ordina casualmente i giocatori
        while (giocatori.get(0).getNome().equals("Computer"));

        for (Giocatore g : giocatori) { // per ogni giocatore imposta il ruolo in base ai parametri
            g.setStato(Action.wait);
            g.resetMano();
            if (g.getNome().equals("Computer")) {
                g.setStrategia(new StrategiaComputer());
                this.computer = g;
            }
            else if (g.equals(mazziere)) {
                g.setStrategia(new StrategiaMazziere());
                this.mazziere = g;
            }
            else
                g.setStrategia(new StrategiaGiocatore());
        }
        giocatori.remove(mazziere);
        giocatori.add(mazziere);        // inseriamo il mazziere per ultimo
    }

    public void pesca(){    // pescata
        Giocatore giocatore = getGiocatoreAttuale();
        Carta cartaPescata = MazzoIteratorSingleton.getInstance().next();
        notificaOsservatore(Action.pescato, cartaPescata.getImagePath());

        if (cartaPescata.matta() && !giocatore.getNome().equals("Computer"))
                notificaOsservatore(Action.matta);  // gestione della matta se pescata dal computer
        else {
            giocatore.aggiungiCarta(cartaPescata);

            if (giocatore.getStato() == Action.busted)
                notificaOsservatore(Action.busted);     // notifica se un giocatore sballa
        }
                                                                            // l'immagine della carta pescata
    }

    public void stai() {    // fine del turno
        // in base al valore notifica la UI
        if (getGiocatoreAttuale().getMano().getValore() == 7.5)
            notificaOsservatore(Action.setteMezzo);
        else if(!(getGiocatoreAttuale().getStato() == Action.busted))
            getGiocatoreAttuale().setStato(Action.results);

        scorriGiocatori();

    }

    private void scorriGiocatori(){
        //metodo per tenere conto dello stato della partita e del giocatore attuale
        indiceScorrimento += 1;
        int statoPartita = indiceScorrimento / giocatori.size();


        if(indiceScorrimento % giocatori.size() == 0) {
            if (statoPartita == 1) {
                for (Giocatore g : giocatori)
                    g.setStato(Action.wait);
                notificaOsservatore(Action.match, "valore piatto: " + getPiatto());
            }
            else if (statoPartita == 2) {
                declareWinners(); // dichiarazione dei vincitori
                for (Giocatore g : giocatori)
                    g.setStato(Action.results);     // aggiornamento dell'interfaccia dei giocatori
                                                    // dopo la riscossione dei gettoni
                notificaOsservatore(Action.results);// notifica del passaggio alla schermata dei risultati
                if (gameSettings.getSettings().getWinners().contains(computer)) {
                    notificaOsservatore(Action.saveComputerWin); /* notifica per attivare il salvataggio delle
                                                                    vittorie del computer */
                }
            }
        }

        if(getGiocatoreAttuale().getNome().equals("Computer")) // gestione del computer quando è il suo turno
            if(statoPartita == 0)                           // nello stato delle quote allora il computer versa
                setQuota(getPiatto()/giocatori.size());     // il computer versa basandosi sulla media delle quote
            else if(statoPartita == 1)
                    pesca();                                // attiviamo la pescata se siamo nello stato successivo

        if(statoPartita == 1) {         //  segnalazione a schermo per il turno del giocatore
            notificaOsservatore(Action.stampa, "E'il turno di " + getGiocatoreAttuale().getNome());
            getGiocatoreAttuale().setStato(Action.match);
        }
    }

    private void declareWinners() { // metodo per dichiarare i vincitori e riscuotere le somme vinte
        mazziere.riscuoti(getPiatto());
        ArrayList<Giocatore> winners = new ArrayList<>();
        ArrayList<Giocatore> losers = new ArrayList<>();
        double valoreMazziere = this.mazziere.getMano().getValore();

        if(valoreMazziere>7.5) valoreMazziere = 0.0;

        for (Giocatore giocatore : giocatori) { // per ogni giocatore compariamo il suo risultato
                                                // con quello del mazziere
            if (giocatore.isMazziere())
                continue;
            if (giocatore.getMano().getValore() <= 7.5 && giocatore.getMano().getValore() > valoreMazziere) {
                winners.add(giocatore); // aggiungiamo il giocatore all'array di vincitori
                giocatore.riscuoti(mazziere.daiGettoniStrat(puntate.get(giocatori.indexOf(giocatore))));
            } else
                losers.add(giocatore); // aggiungiamo il giocatore all'array di sconfitti
        }
        gameSettings.getSettings().setWinners(winners); // aggiorniamo i settings
        gameSettings.getSettings().setLosers(losers);
        if (winners.contains(computer)) // se il computer ha vinto aggiorniamo le sue vittorie
            notificaOsservatore(Action.saveComputerWin);
    }

    public Giocatore getGiocatoreAttuale() {   // metodo per acquisire il giocatore attuale
        return giocatori.get(indiceScorrimento % giocatori.size());
    }

    public void setQuota(int quotaVersata) {  // metodo per scalare la quota puntata
        Giocatore attuale = getGiocatoreAttuale();
        puntate.add(attuale.daiGettoniStrat(quotaVersata));

        // notifica a schermo della quota versata
        notificaOsservatore(Action.stampa, attuale.getNome() + " ha versato " + quotaVersata);
        attuale.setStato(Action.bidded);
        scorriGiocatori();  // scorrimento dei giocatori

        if (getGiocatoreAttuale().isMazziere())
            setQuota(0);     // se il giocatore è il mazziere non versa nulla e passa
        else if(indiceScorrimento % giocatori.size() != 0 && getGiocatoreAttuale().getStato() != Action.match)
            // se non è il mazziere notifica a schermo per il giocatore che dovrà puntare
            getGiocatoreAttuale().setStato(Action.bid);
    }

    public void setMatta(int matta) {   // metodo per settare il valore della matta
        getGiocatoreAttuale().aggiungiCarta(new Carta(matta, "Matta"));
    }
}
