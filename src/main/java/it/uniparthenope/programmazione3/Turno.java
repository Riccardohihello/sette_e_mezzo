package it.uniparthenope.programmazione3;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Turno {
    private StatoTurno statoTurno;
    private final Observer osservatore;
    private final GestoreGiocatori gestoreGiocatori;
   private final GestoreMazzo gestoreMazzo;
    private final RegistroVincite registroVincite;
    private final Scanner sc = new Scanner(System.in);
    public int piatto;
    public int numeroPuntate = 0;
    public Random random = new Random();
    public int quotaDaVersare = random.nextInt(1,11);

    public Turno(Observer osservatore, ObservableList<String> nomiGiocatori) {
        this.osservatore = osservatore;
        gestoreGiocatori = new GestoreGiocatori(nomiGiocatori);
        notificaMazziere();
        gestoreMazzo = new GestoreMazzo();
        registroVincite = RegistroVincite.getInstance();
        setStatoTurno(new StatoRaccoltaQuote());

        eseguiTurno();
    }

    private void eseguiTurno() {
        eseguiAzione();
        eseguiAzione();
        eseguiAzione();
        eseguiAzione();
        stampaRisultati();
    }

   public Mazzo getMazzo() {
        return gestoreMazzo.getMazzo();
    }
    public Mazziere getMazziere() {
        return gestoreGiocatori.getMazziere();
    }
    public void notificaMazziere() {
        String args = "Il mazziere è " + gestoreGiocatori.getMazziere().getNome();
        notificaOsservatore("mazziere", args);
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

    private void notificaOsservatore(String label, String args) {
        if (osservatore != null) {
            osservatore.update(label, args);
        }
    }

    public void eseguiAzione() {
        statoTurno.eseguiAzione(this);
    }

    private void stampaRisultati() {
        statoTurno.eseguiAzione(this);
        for (Giocatore giocatore : gestoreGiocatori.getGiocatori()) {
            System.out.println(giocatore.getNome() + " ha ora " + giocatore.gettoni + " gettoni.");
            String args = giocatore.getNome() + " ha ora " + giocatore.gettoni + " gettoni";
            notificaOsservatore("risultati", args);
            inviaPartecipanti(giocatore, gestoreGiocatori.getGiocatori().size());
        }
    }

    private void inviaPartecipanti(Giocatore giocatori, int size) {
        if (osservatore != null) {
            osservatore.partecipanti(giocatori, size);
        }
    }

    public void setStatoTurno(StatoTurno stato) {
        this.statoTurno = stato;
    }

    public void setQuota(int i) {
    }
}



