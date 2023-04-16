
public class Mazziere extends Giocatore {

    private final Mazzo mazzo;

    public Mazziere(String nome, Mazzo mazzo) {
        super(nome);
        this.mazzo = mazzo;
    }
    public void getMazzo() { //metodo per stampare il mazzo di carte del mazziere
       mazzo.stampa();
    }

}