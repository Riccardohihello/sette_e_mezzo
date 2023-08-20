package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.model.Giocatore;
import it.uniparthenope.programmazione3.model.Mano;

import java.util.ArrayList;
import java.util.List;

public interface Observer {

    List<String> nomiGiocatori = new ArrayList<>();
    List<String> saldoGiocatori = new ArrayList<>();
     void update(String label, String args, Mano mano);
     void partecipanti(ArrayList<Giocatore> giocatori, int size);

     void raccoltaQuote(ArrayList<Giocatore> giocatori);
     void eseguiMatch(ArrayList<Giocatore> giocatori);
}