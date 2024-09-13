package it.uniparthenope.programmazione3.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MazzoIterator implements Iterator<Carta> {

    private int iteratorPosition = 0;
    private final ArrayList<Carta> carte;

    public MazzoIterator() {
        this.carte = new ArrayList<>();
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

    @Override
    public boolean hasNext() {
        return iteratorPosition < carte.size();
    }
    @Override
    public Carta next() {
        int temp = iteratorPosition;
        if (hasNext())
            iteratorPosition++;
        else
            iteratorPosition = 0;
        return carte.get(temp);
    }


}