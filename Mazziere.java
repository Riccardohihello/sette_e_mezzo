
public class Mazziere extends Giocatore {

    public Mazziere(Giocatore g) {
        super(g.getNome());

        System.out.println("\nMazziere " + getNome() + " creato");
    }
}