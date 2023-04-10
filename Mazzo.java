
import java.util.ArrayList;
import java.util.Collections;


class Mazzo {
    private ArrayList<Carta> carte;

    public Mazzo() {
        carte = new ArrayList<Carta>();
        String[] semi = {"coppe", "denari", "spade", "bastoni"};
        for (int i = 0; i < 10; i++) {
            for (String seme : semi) {
                carte.add(new Carta(i+1, seme));
            }
        }
    }

    public Carta pesca() {
        if (carte.isEmpty()) {
            System.out.println("Il mazzo è vuoto");
        }
        return carte.remove(0);
    }
    public void mescola() {
        Collections.shuffle(carte);
    }
    public void stampa() {
        for (Carta carta : carte) {
            String seme = carta.getSeme();
            int valore = carta.getValore();
            System.out.println("Valore: " + valore + " Seme: " + seme);
        }
    }
}