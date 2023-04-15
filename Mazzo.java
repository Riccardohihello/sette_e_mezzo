
import java.util.ArrayList;
import java.util.Collections;


class Mazzo {
    private final ArrayList<Carta> carte;

    private Mazzo() {
        carte = new ArrayList<Carta>();
        
        String[] semi = {"coppe", "denari", "spade", "bastoni"};
        for (int i = 0; i < 10; i++) {
            for (String seme : semi) {
                carte.add(new Carta(i+1, seme));
            }
        }
    }

    private static Mazzo instanza;
    public static Mazzo creaMazzo()
    {
        if(instanza == null) {
            instanza = new Mazzo();
        } return instanza;
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