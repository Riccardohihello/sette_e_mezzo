package it.uniparthenope.programmazione3;

public class Computer extends GiocatoreAbstract{
    private Strategia strat;

    public void stileDiGioco() {
        strat.strategiaDiGioco(mano);

    }

    private Computer() {
        this.nome = "COMPUTER";
        this.gettoni = Costanti.m;
    }

    private static Computer instanza = null;
    public static Computer getInstanza()
    {
        if(instanza == null) {
            instanza = new Computer();
        }
        return instanza;
    }


}
