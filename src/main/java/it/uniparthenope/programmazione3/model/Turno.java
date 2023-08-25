package it.uniparthenope.programmazione3.model;

import it.uniparthenope.programmazione3.observerPattern.Observer;
import it.uniparthenope.programmazione3.statePattern.StatoRaccoltaQuote;
import it.uniparthenope.programmazione3.statePattern.StatoTurno;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Objects;

public class Turno {
    private StatoTurno statoTurno;
    private final Observer osservatore;

    public Turno(Observer osservatore) {
        StatistichePartita.getInstance().sceltaMazziere();
        this.osservatore = osservatore;
        notificaMazziere();
        Mazzo.getInstance().mischia();
        setStatoTurno(new StatoRaccoltaQuote());
        eseguiTurno();
    }

    private void eseguiTurno() {
        eseguiAzione();
    }

    public void notificaMazziere() {
        String args = "Il mazziere è " + StatistichePartita.getInstance().getMazziere().getNome();
        notificaOsservatore("mazziere", args,null);
    }

    public ArrayList<Giocatore> getGiocatori() {
        return StatistichePartita.getInstance().getGiocatori();
    }

    public void notificaOsservatore(String label, String args, Mano mano) {
        if (osservatore != null) {
            osservatore.update(label, args,mano);
        }
    }

    public void eseguiAzione() {
        statoTurno.eseguiAzione(this);
    }

    public void inviaPartecipanti(ArrayList<Giocatore> giocatori, int size) {
        if (osservatore != null) {
            osservatore.partecipanti(giocatori, size);
        }
    }


    public void setStatoTurno(StatoTurno stato) {
        this.statoTurno = stato;
    }

    public void notificaQuota(ArrayList<Giocatore> giocatori) {
        //String args = giocatore.getNome() + "deve versare: ";
        osservatore.raccoltaQuote(giocatori);
    }
    public void eseguiMatch(ArrayList<Giocatore> giocatori) {
        osservatore.eseguiMatch(giocatori);
    }

    //Ricava dai campi di carta il path dell'immagine relativa a quella carta
    private String getCartaImagePath(Carta carta) {
        String seme = carta.getSeme();
        String valore = String.valueOf((int) carta.getValore());
        String nomeCartella = seme.substring(0, 1).toUpperCase() + seme.substring(1);
        String imagePath = String.format("/it/uniparthenope/programmazione3/images/Carte/%s/%s%s.png", nomeCartella, valore, seme.charAt(0));
        return Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm();
    }

    public void pesca (Giocatore giocatore) {
                if(Mazzo.getInstance().hasNext()) {
                    Carta c = Mazzo.getInstance().next();
                    giocatore.addCarta(c);
                    notificaOsservatore("carta",getCartaImagePath(c),giocatore.getMano());
                }
    }

    public void stai (Giocatore giocatore) {
                Mano  m = giocatore.getMano();
                if(m.getValore() > 7.5) {
                    notificaOsservatore("valore","sballato",null);
                } else if (m.getValore() < 7.5) {
                    notificaOsservatore("valore","stai con un valore di "+ m.getValore(),null);
                } else if (m.getValore() == 7.5) {
                    notificaOsservatore("valore", "hai il massimo",null);
                }
            }

    public int getNumeroVincitori() {
        return this.getVincitori().size();
    }

    public ArrayList<Giocatore> getVincitori() {
        ArrayList<Giocatore> vincitori = new ArrayList<>();
        for (Giocatore giocatore : StatistichePartita.getInstance().getGiocatori()) {
            if (giocatore.getMano().getValore() > StatistichePartita.getInstance().getMazziere().getMano().getValore()) {
                vincitori.add(giocatore);
            }
        }
        return vincitori;
    }
}



