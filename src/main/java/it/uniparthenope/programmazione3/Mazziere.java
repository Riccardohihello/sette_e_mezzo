package it.uniparthenope.programmazione3;


public class Mazziere extends GiocatoreAbstract {

    public Mazziere(Giocatore g) {
        this.nome = g.getNome();
        this.mano = new Mano();

        System.out.println("\nMazziere " + this.nome + " creato");
    }

/*    public void daiCarte(Mazzo mazzo, ArrayList<Giocatore> giocatori) {
        for (Giocatore g : giocatori) {
            g.;
        }
    }*/
}