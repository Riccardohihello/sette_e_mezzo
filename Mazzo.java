import java.util.ArrayList;
import java.util.Collections;

class Mazzo {
    private final ArrayList<Carta> carte;

    private Mazzo() {
        this.carte = new ArrayList<>();

        String[] semi = {"coppe", "denari", "spade", "bastoni"};
        for (String seme : semi) {
            for (int i = 0; i < 10; i++) {
                this.carte.add(new Carta(i+1, seme));
            }
        }
    }

    private static Mazzo instanzaMazzo;
    public static Mazzo creaMazzo()
    {
        if(instanzaMazzo == null) {
            instanzaMazzo = new Mazzo();
        }
        return instanzaMazzo;
    }

    public Carta pesca() {
        if (this.carte.isEmpty()) {
            System.out.println("Il mazzo è vuoto");
        }
        return this.carte.remove(carte.size()-1);
    }
    public void mischia() {
        Collections.shuffle(this.carte);
    }
    public void addCarta(Carta carta) {
        this.carte.add(carta);
    }

    public int getValore() {
        return this.carte.size();
    };

    public void stampa() {
        for (Carta carta : this.carte) {
            System.out.println("Valore: " + carta.getValore() + " Seme: " + carta.getSeme());
        }
    }
}