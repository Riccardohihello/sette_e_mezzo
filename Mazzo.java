
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
        return this.carte.remove(0);
    }
    public void mescola() {
        Collections.shuffle(this.carte);
    }
    public void rimuovi() {
        carte.remove(0); // metodo non completo, rimuove solo dall'indice 0
    }
    public void stampa() {
        for (Carta carta : this.carte) {
            String seme = carta.getSeme();
            int valore = carta.getValore();
            System.out.println("Valore: " + valore + " Seme: " + seme);
        }
    }
}