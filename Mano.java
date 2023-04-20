import java.util.ArrayList;

public class Mano {
    private final ArrayList<Carta> carte;
    private int punti;

    public Mano() {
        this.carte = new ArrayList<>();
    }
    public void addCarta(Carta c){
        this.carte.add(c);
        this.punti+=c.getValore();
    }
    public void svuotaMano(){
        this.carte.clear();
    }

}