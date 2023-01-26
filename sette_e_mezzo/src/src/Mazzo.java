package src;

import java.util.ArrayList;
import java.util.Collections;
import src.Carta;

class Mazzo {
    private ArrayList<Carta> carte;

    public Mazzo() {
        carte = new ArrayList<Carta>();
        String[] semi = {"coppe", "denari", "spade", "bastoni"};
        for (int i = 0; i < 10; i++) {
            for (String seme : semi) {
                carte.add(new Carta(i, seme));
            }
        }
    }

    public Carta pesca() {
        if (carte.isEmpty()) {
            system.out.println("Il mazzo è vuoto");
        }
        return carte.remove(0);

    public void mescola() {
        Collections.shuffle(carte);
    }

}