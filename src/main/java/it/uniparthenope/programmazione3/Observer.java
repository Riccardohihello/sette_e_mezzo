package it.uniparthenope.programmazione3;

import java.util.ArrayList;
import java.util.List;

public interface Observer {

    List<String> nomiGiocatori = new ArrayList<>();
    public void update(String label, String args);
    public void partecipanti(Giocatore giocatori,int size);

}