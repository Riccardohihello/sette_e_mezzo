package it.uniparthenope.programmazione3.partita;

import it.uniparthenope.programmazione3.observerPattern.Observer;
import it.uniparthenope.programmazione3.statePattern.StatoTurno;
import it.uniparthenope.programmazione3.statePattern.StatoRaccoltaQuote;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

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
        eseguiAzione();
        eseguiAzione();
        eseguiAzione();
        stampaRisultati();
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

    private void notificaOsservatore(String label, String args, Mano mano) {
        if (osservatore != null) {
            osservatore.update(label, args,mano);
        }
    }

    public void eseguiAzione() {
        statoTurno.eseguiAzione(this);
    }

    private void stampaRisultati() {
        statoTurno.eseguiAzione(this);
        for (Giocatore giocatore : gestoreGiocatori.getGiocatori()) {
            System.out.println(giocatore.getNome() + " ha ora " + giocatore.getGettoni() + " gettoni.");
            String args = giocatore.getNome() + " ha ora " + giocatore.getGettoni() + " gettoni";
            notificaOsservatore("risultati", args, null);
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

    public void notificaQuota(ArrayList<Giocatore> giocatori) {
        //String args = giocatore.getNome() + "deve versare: ";
        osservatore.raccoltaQuote(giocatori);
    }
    public CompletableFuture<Integer> getQuotaCompletableFuture() {
        return quotaCompletableFuture;
    }

    //Ricava dai campi di carta il path dell'immagine relativa a quella carta
    private String getCartaImagePath(Carta carta) {
        String seme = carta.getSeme();
        String valore = String.valueOf((int) carta.getValore());
        char inizialeSeme = Character.toUpperCase(seme.charAt(0));
        String nomeCartella = Character.toUpperCase(inizialeSeme) + seme.substring(1);
        String imagePath = "/it/uniparthenope/programmazione3/images/Carte/" + nomeCartella + "/" + valore + inizialeSeme + ".png";
        return Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm();
    }

    public String pesca () {
        for (Giocatore giocatore : gestoreGiocatori.getGiocatori()) {
            if (giocatore.getNome().equals("Paolo")) {
                if(gestoreMazzo.getMazzo().hasNext()) {
                    Carta c = gestoreMazzo.getMazzo().next();
                    giocatore.addCarta(c);
                    notificaOsservatore("carta",getCartaImagePath(c),giocatore.getMano());
                }
                return "Paolo ha pescato";
            } else return "Non è il tuo turno!";
        }
        return "Ciao";
    }

    public void stai () {
        for (Giocatore giocatore : gestoreGiocatori.getGiocatori()) {
            if (giocatore.getNome().equals("Paolo")) {
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
    }
}



