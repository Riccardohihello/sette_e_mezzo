package it.uniparthenope.programmazione3.model;
import it.uniparthenope.programmazione3.strategyPattern.StrategiaComputer;

public class Computer extends GiocatoreAbstract {

    Computer() {
        this.strat = new StrategiaComputer();
        this.nome = "COMPUTER";
        this.mano = new Mano();
    }

    private static Computer instance;
    public static Computer getInstance()
    {
        if(instance == null) {
            instance = new Computer();
        }
        return instance;
    }

    public int QuotaDaVersare(int piatto,int puntatePrecedenti){
        int quotaDaVersare;
        if (piatto==0)
            quotaDaVersare = 10;
        else
            quotaDaVersare = piatto / puntatePrecedenti;
        return quotaDaVersare;
    }


}
