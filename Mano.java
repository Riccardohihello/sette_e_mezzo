import java.util.ArrayList;

public class Mano implements CartaInterface {
    private final ArrayList<Carta> carte;
    private int valore;

    public Mano() {
        this.carte = new ArrayList<>();
    }
    public void addCarta(Carta c){
        this.carte.add(c);
        this.valore=c.getValore();
    }

    public void removeCarta() {
        this.carte.clear();
    }

    @Override
    public int getValore(){
        return this.valore;
    }
}