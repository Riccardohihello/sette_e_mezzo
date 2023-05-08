import java.util.ArrayList;

public class Mano implements CartaInterface {
    private final ArrayList<Carta> ManoDicarte;
    private int valore;

    public Mano() {
        this.ManoDicarte = new ArrayList<>();
    }
    public void addCarta(Carta c){
        this.ManoDicarte.add(c);
        this.valore+=c.getValore();
    }

    public Carta cartaPescata(){
        return this.ManoDicarte.get(this.ManoDicarte.size()-1);
    }

    @Override
    public int getValore(){
        return this.valore;
    }
}