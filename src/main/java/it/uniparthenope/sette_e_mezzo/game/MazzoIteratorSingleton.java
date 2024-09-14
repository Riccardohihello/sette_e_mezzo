package it.uniparthenope.sette_e_mezzo.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public enum MazzoIteratorSingleton implements Iterator<Carta> {
    //classe rappresentante mazzo di carte, che fa uso del pattern singleton
    // implementata tramite enum
    INSTANCE;

    private int iteratorPosition;
    private final ArrayList<Carta> carte;

    MazzoIteratorSingleton() {
        this.carte = new ArrayList<>();
        String[] semi = {"Coppe", "Denari", "Spade", "Bastoni"};
        for (String seme : semi) {
            for (int i = 0; i < 10; i++) {
                this.carte.add(new Carta(i+1, seme));
            }
        }
        this.iteratorPosition = 0;
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

    // Metodo per ottenere l'istanza del singleton
    public static MazzoIteratorSingleton getInstance() {
        return INSTANCE;
    }
}