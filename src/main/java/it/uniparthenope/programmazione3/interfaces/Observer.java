package it.uniparthenope.programmazione3.interfaces;

import it.uniparthenope.programmazione3.classes.Giocatore;
import it.uniparthenope.programmazione3.classes.Mano;

import java.util.ArrayList;
import java.util.List;

public interface Observer {

    List<String> nomiGiocatori = new ArrayList<>();
     void update(String label, String args, Mano mano);
     void partecipanti(Giocatore giocatori, int size);

     void raccoltaQuote(ArrayList<Giocatore> giocatori);
}