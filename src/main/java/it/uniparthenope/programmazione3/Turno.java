package it.uniparthenope.programmazione3;

import it.uniparthenope.programmazione3.classes.*;
import it.uniparthenope.programmazione3.interfaces.Observer;
import it.uniparthenope.programmazione3.interfaces.StatoTurno;
import it.uniparthenope.programmazione3.states.StatoRaccoltaQuote;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class Turno {
    private StatoTurno statoTurno;
    private final Observer osservatore;
    private final GestoreGiocatori gestoreGiocatori;
    private final GestoreMazzo gestoreMazzo;
    private final RegistroVincite registroVincite;
    private final Scanner sc = new Scanner(System.in);
    public int piatto;
    public int numeroPuntate = 0;
    public Random random = new Random();
    private CompletableFuture<Integer> quotaCompletableFuture;
    private CompletableFuture<Integer> turnoCompletableFuture;
    public int quotaDaVersare = random.nextInt(1,11);

    public Turno(Observer osservatore, ObservableList<String> nomiGiocatori) {
        this.osservatore = osservatore;
        gestoreGiocatori = new GestoreGiocatori(nomiGiocatori);
        notificaMazziere();
        gestoreMazzo = new GestoreMazzo();
        gestoreMazzo.mischiaMazzo();
        registroVincite = RegistroVincite.getInstance();
        setStatoTurno(new StatoRaccoltaQuote());
        eseguiTurno();
    }

    private void eseguiTurno() {
        eseguiAzione();
    }

    public void setRegistroVincite() {
        double valoreMax = 0.0;
        for(Giocatore giocatore : getGiocatori()) {
            if (giocatore.getMano().getValore() > valoreMax && giocatore.getMano().getValore()<= 7.5) {
                registroVincite.reset();
                registroVincite.registraVincitori(giocatore);
            } else if (giocatore.getMano().getValore() == valoreMax) {
                registroVincite.registraVincitori(giocatore);
            }
        }
    }
    public void mischia() {
        gestoreMazzo.mischiaMazzo();
    }

    public Mazziere getMazziere() {
        return gestoreGiocatori.getMazziere();
    }
    public void notificaMazziere() {
        String args = "Il mazziere è " + gestoreGiocatori.getMazziere().getNome();
        notificaOsservatore("mazziere", args,null);
    }
    public Computer getComputer() {
        return gestoreGiocatori.getComputer();
    }
    public ArrayList<Giocatore> getGiocatori() {
        return gestoreGiocatori.getGiocatori();
    }
    public ArrayList<Giocatore> getVincitori() {
        return registroVincite.getVincitori();
    }
    public int getNumeroVincitori() {
        return registroVincite.getVincitori().size();
    }
    public void registraVincitori(Giocatore g) {
        registroVincite.registraVincitori(g);
    }

    public void notificaOsservatore(String label, String args, Mano mano) {
        if (osservatore != null) {
            osservatore.update(label, args,mano);
        }
    }

    public void eseguiAzione() {
        statoTurno.eseguiAzione(this);
    }

    private void stampaRisultati() {
        statoTurno.eseguiAzione(this);
        inviaPartecipanti(gestoreGiocatori.getGiocatori(), gestoreGiocatori.getGiocatori().size());
        for (Giocatore giocatore : gestoreGiocatori.getGiocatori()) {
            System.out.println(giocatore.getNome() + " ha ora " + giocatore.gettoni + " gettoni.");
            String args = giocatore.getNome() + " ha ora " + giocatore.gettoni + " gettoni";
            notificaOsservatore("risultati", args, null);

        }
    }
    public void inviaPartecipanti(ArrayList<Giocatore> giocatori, int size) {
        if (osservatore != null) {
            osservatore.partecipanti(giocatori, size);
        }
    }


    public void setStatoTurno(StatoTurno stato) {
        this.statoTurno = stato;
    }

    public void setQuota(int i) {
    }

    public void notificaQuota(ArrayList<Giocatore> giocatori) {
        //String args = giocatore.getNome() + "deve versare: ";
        osservatore.raccoltaQuote(giocatori);
    }
    public void eseguiMatch(ArrayList<Giocatore> giocatori) {
        osservatore.eseguiMatch(giocatori);
    }
    public CompletableFuture<Integer> getQuotaCompletableFuture() {
        return quotaCompletableFuture;
    }
    public CompletableFuture<Integer> getTurnoCompletableFuture() { return turnoCompletableFuture; }
    //Ricava dai campi di carta il path dell'immagine relativa a quella carta
    private String getCartaImagePath(Carta carta) {
        String seme = carta.getSeme();
        String valore = String.valueOf((int) carta.getValore());
        char inizialeSeme = Character.toUpperCase(seme.charAt(0));
        String nomeCartella = Character.toUpperCase(inizialeSeme) + seme.substring(1);
        String imagePath = "/it/uniparthenope/programmazione3/images/Carte/" + nomeCartella + "/" + valore + inizialeSeme + ".png";
        return getClass().getResource(imagePath).toExternalForm();
    }

    public String pesca (Giocatore giocatore) {
                if(gestoreMazzo.mazzo.hasNext()) {
                    Carta c = gestoreMazzo.mazzo.next();
                    giocatore.addCarta(c);
                    notificaOsservatore("carta",getCartaImagePath(c),giocatore.getMano());
                }
                return "Paolo ha pescato";
    }

    public void stai (Giocatore giocatore) {
                Mano  m = giocatore.getMano();
                if(m.getValore() > 7.5) {
                    notificaOsservatore("valore","il bro ha sballato",null);
                } else if (m.getValore() < 7.5) {
                    notificaOsservatore("valore","il bro ha "+String.valueOf(m.getValore()),null);
                } else if (m.getValore() == 7.5) {
                    notificaOsservatore("valore", "il bro c'ha le palle",null);
                }
            }
}



