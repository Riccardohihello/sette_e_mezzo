package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.game.*;
import it.uniparthenope.programmazione3.strategyPattern.Strategia;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaComputer;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaGiocatore;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaMazziere;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Partita  {
    private final ArrayList<gameObserver> osservatori = new ArrayList<>();
    private int indiceScorrimento = 0;
    private final ArrayList<Giocatore> winners = new ArrayList<>();
    private double winValue = -1;
    private int turniGiocati = 0;
    private final ArrayList<Giocatore> giocatori = new ArrayList<>();
    MazzoIterator mazzoIterator = new MazzoIterator();
    public int piatto;

    // Costruttore privato per impedire istanze multiple
    public Partita() {
        giocatori.addAll(SettingsSingleton.getInstance().getListaGiocatori());
        mazzoIterator.mischia();
        aggiungiGiocatori();
        //Ciclo per posizionare mazziere alla fine (a scopo di debug)
        while(giocatori.get(giocatori.size()-1).getStato().equals(Action.wait) || giocatori.get(giocatori.size()-1).getStato().equals(Action.computer))
            Collections.shuffle(giocatori);
        //if (giocatori.get(indiceScorrimento).getStrategia().getClass().equals(StrategiaMazziere.class))
            //  scorriGiocatori();
    }

    public void notificaOsservatore(Action action, String... message) {
        if (!osservatori.isEmpty())
            for (gameObserver observer : this.osservatori)
                observer.update(action,message);
    }

    public void addOsservatore(gameObserver osservatore) {
        this.osservatori.add(osservatore);
    }

    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    private void aggiungiGiocatori() {
        Random random = new Random();
        int rand;
        do {
             rand = random.nextInt(giocatori.size() - 1);
        }while(giocatori.get(rand).getStrategia()!=null && giocatori.get(rand).getStrategia().getClass().equals(StrategiaMazziere.class) || giocatori.get(rand).getNome().equals("Computer"));

        for (Giocatore g : giocatori) {
            g.setStato(Action.wait);
            g.resetMano();
            if (g.getNome().equals("Computer"))
                g.setStrategia(new StrategiaComputer());
            else if (g.getNome().equals(giocatori.get(rand).getNome())) {
                g.setStrategia(new StrategiaMazziere());
                g.setStato(Action.mazziere);

            }else
                g.setStrategia(new StrategiaGiocatore());
        }
    }

    public void pesca(){
        Giocatore giocatore = giocatori.get(indiceScorrimento);
        giocatore.aggiungiCarta(mazzoIterator.next());
        notificaOsservatore(Action.pescato);
        if(giocatore.getMano().getValore()>7.5) {
            giocatore.setStato(Action.busted);
            notificaOsservatore(Action.busted);
            stai();
        }
    }

    private void resetStato() {
        System.out.println("Resetto");
        SettingsSingleton.getInstance().updateList(giocatori);
        notificaOsservatore(Action.reset);
        /*
        for (Giocatore g : giocatori) {
            g.setStato(stato);
            //Se è uguale a wait ricomincia una nuova partita e resetto anche le variabili
            if (stato == Action.wait) {
                g.resetMano();
            }
        }
        if (stato == Action.wait) {
            winners.clear();
            winValue = -1;
            piatto = 0;
            mazzoIterator.mischia();
            aggiungiGiocatori();
            //Ciclo per posizionare mazziere alla fine (a scopo di debug)
            while(giocatori.get(giocatori.size()-1).getStato().equals(Action.wait) || giocatori.get(giocatori.size()-1).getStato().equals(Action.computer))
                Collections.shuffle(giocatori);
        }*/
    }
    public void stai() {
        Giocatore giocatore = giocatori.get(indiceScorrimento);
        if (giocatore.getStato()!=Action.busted)
            giocatore.setStato(Action.results);
        if (giocatore.getMano().getValore() == 7.5)
            notificaOsservatore(Action.setteMezzo);
        if (indiceScorrimento == giocatori.size()-1){
            calcoloRisultati();
            System.out.println("\nSto prima del reset Abbiamo "+turniGiocati+" < "+SettingsSingleton.getInstance().getNumeroTurni()+"\n");
               if (SettingsSingleton.getInstance().getCountTurni() < SettingsSingleton.getInstance().getNumeroTurni()) {
                   SettingsSingleton.getInstance().setCountTurni();
                   resetStato();
                }
          }else{
            scorriGiocatori();
            prossimoGiocatore(Action.match);
            notificaOsservatore(Action.clear);
        }
    }
    public void calcoloRisultati() {
        turniGiocati++;
        notificaOsservatore(Action.results);
        //Decidi vincitore()
        for (Giocatore g : giocatori){
            g.setStato(Action.results);
            if (g.getMano().getValore() > winValue && g.getMano().getValore() <= 7.5) {
                winValue = g.getMano().getValore();
                winners.clear();
                winners.add(g);
            } else if (g.getMano().getValore() == winValue ) {
                winners.add(g);
            }
        }
        if (winners.isEmpty()) {
            notificaOsservatore(Action.stampa,"Non ha vinto nessuno...");
        } else {
            int quota = piatto / winners.size();
            for (Giocatore g : winners) {
                System.out.println("Vincitore: " + g.getNome() + "\n");
                g.riscuoti(quota);
            }
        }
    }

    public void scorriGiocatori(){
        indiceScorrimento += 1;
        indiceScorrimento %= giocatori.size()+1;
    }

    public Giocatore getAttuale() {
        return giocatori.get(indiceScorrimento);
    }

    public String getManoGiocatore(){
        if (giocatori.get(indiceScorrimento).getMano().cartaPescata()!=null)
            return giocatori.get(indiceScorrimento).getMano().cartaPescata().getImagePath();
        else
            return null;
    }

    private Strategia strategiaGiocatore(){
        return giocatori.get(indiceScorrimento).getStrategia();
    }

    public void riempiPiatto(int quota){
        this.piatto += quota;
        this.giocatori.get(indiceScorrimento).puntataDaVersare(quota);
    }

    public void sceltaComputer() {
        Giocatore computer = getAttuale();
        if (computer.getStato() != Action.results ||computer.getStato() != Action.busted) {
            Thread computerThread = new Thread(() -> {
                do {
                    pesca();
                    try {
                        // Aggiungi un ritardo di 1 secondo tra le pescate del computer
                        Thread.sleep(2000); // Un secondo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (computer.scelta());
                stai();
            });
            computerThread.start();
        }
    }
    private void prossimoGiocatore(Action azione) {
        Giocatore prossimo = giocatori.get(indiceScorrimento);
        if (prossimo.getStato().equals(Action.mazziere) && azione.equals(Action.bid)) {
            //Salta automaticamente il mazziere quando lo incontra
            notificaOsservatore(Action.stampa, "Il giocatore " + prossimo.getNome() + " è il mazziere");
            setQuota(0);
        } else if (prossimo.getNome().equals("Computer") && azione.equals(Action.bid)) {
            notificaOsservatore(Action.bid);
        } else if (prossimo.getNome().equals("Computer") && azione.equals(Action.match)) {
            sceltaComputer();
        } else {
            //Avvisa il prossimo giocatore che è il suo turno
            notificaOsservatore(Action.stampa, "E' il turno di " + prossimo.getNome());
            prossimo.setStato(azione);
        }
    }

    public void setQuota(int quotaVersata) {
        Giocatore attuale = giocatori.get(indiceScorrimento);
        if (strategiaGiocatore().getClass().equals(StrategiaComputer.class)) {
            int quotaComputer = piatto/giocatori.size();
            riempiPiatto(attuale.puntataDaVersare(quotaComputer));
            notificaOsservatore(Action.stampa,"Il computer "+attuale.getNome()+" ha versato "+attuale.puntataDaVersare(quotaComputer));
            attuale.setStato(Action.bidded);
        }else if (attuale.getStato()!=Action.mazziere){
            attuale.setStato(Action.bidded);
            notificaOsservatore(Action.stampa,"Il giocatore "+attuale.getNome()+" ha versato "+quotaVersata);
            riempiPiatto(quotaVersata);
        }
        scorriGiocatori();
        if(indiceScorrimento==giocatori.size()) {
            attuale.setStato(Action.bidded);
            scorriGiocatori();
            indiceScorrimento = 0;
            notificaOsservatore(Action.match);

        } else {
            prossimoGiocatore(Action.bid);
        }
    }
}
