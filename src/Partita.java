package src;

import java.util.List;
import java.util.ArrayList;

class Partita {
    private List<Giocatore> giocatori;
    private Mazzo mazzo;
    private Mazziere mazziere;
    private int indiceMazziere = 0;

    public Partita() {
        giocatori = new ArrayList<Giocatore>();
    }

}