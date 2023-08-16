package it.uniparthenope.programmazione3.partita;


public class Mazziere extends GiocatoreAbstract {

    public Mazziere(Giocatore g) {
        this.nome = g.getNome();
        this.mano = new Mano();
        this.gettoni = g.getGettoni();

        System.out.println("\nMazziere " + this.nome + " creato");
    }

    public void pagaVincite(int importo, Giocatore g)
    {
        this.gettoni -= importo;
        g.riscuoti(importo);
    }
/*    public void daiCarte(Mazzo mazzo, ArrayList<Giocatore> giocatori) {
        for (Giocatore g : giocatori) {
            g.;
        }
    }*/
}