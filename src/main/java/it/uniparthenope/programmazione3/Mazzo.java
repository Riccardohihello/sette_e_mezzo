package it.uniparthenope.programmazione3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Mazzo implements Iterator<Carta> {

    private final int carteTotali = 40;
    private int iteratorPosition = 0;
    private final ArrayList<Carta> carte;

    private Mazzo() {
        this.carte = new ArrayList<>();

        String[] semi = {"coppe", "denari", "spade", "bastoni"};
        for (String seme : semi) {
            for (int i = 0; i < 10; i++) {
                this.carte.add(new Carta(i+1, seme));
            }
        }
        this.mischia();
    }

    private static Mazzo instanzaMazzo;
    public static Mazzo creaMazzo()
    {
        if(instanzaMazzo == null) {
            instanzaMazzo = new Mazzo();
        }
        return instanzaMazzo;
    }
    public int getIteratorPosition() {
        return iteratorPosition;
    }
    public void mischia() {
        Collections.shuffle(this.carte);
        iteratorPosition = 0;
    }
    @Override
    public boolean hasNext() {
        if(iteratorPosition < carteTotali)
            return true;
        else
            return false;
    }
    @Override
    public Carta next() {
        if (iteratorPosition < carte.size() - 1) {
            iteratorPosition++;
        } else {
            iteratorPosition = 0;
        }

        Carta c = carte.get(iteratorPosition);
        return c;
    }

    public Carta previous() {
        if (iteratorPosition > 0) {
            iteratorPosition--;
        } else {
            iteratorPosition = carte.size() - 1; // Torna all'ultima carta se sei all'inizio della lista
        }

        Carta c = carte.get(iteratorPosition);
        return c;
    }


}