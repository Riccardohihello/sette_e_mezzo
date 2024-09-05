package it.uniparthenope.programmazione3.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MazzoIterator implements Iterator<Carta> {

    private int iteratorPosition = 0;
    private final ArrayList<Carta> carte;

    public MazzoIterator() {
        this.carte = new ArrayList<>();
        this.carte.add(new Carta(10, "Denari"));
        String[] semi = {"Coppe", "Denari", "Spade", "Bastoni"};
        for (String seme : semi) {
            for (int i = 0; i < 10; i++) {
                this.carte.add(new Carta(i+1, seme));
            }
        }
    }

    public void mischia() {
        Collections.shuffle(this.carte);
        iteratorPosition = 0;
    }

    public int getIteratorPosition() {
        return iteratorPosition;
    }

    @Override
    public boolean hasNext() {
        int carteTotali = 40;
        return iteratorPosition < carteTotali;
    }
    @Override
    public Carta next() {
        int temp = iteratorPosition;
        if (iteratorPosition < carte.size() - 1) {
            iteratorPosition++;
        } else {
            iteratorPosition = 0;
        }
        return carte.remove(temp);
    }

    public Carta previous() {
        if (iteratorPosition > 0) {
            iteratorPosition--;
        } else {
            iteratorPosition = carte.size() - 1; // Torna all'ultima carta se sei all'inizio della lista
        }

        return carte.get(iteratorPosition);
    }


}