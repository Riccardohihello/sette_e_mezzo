package it.uniparthenope.programmazione3;

public class StrategiaComputer implements Strategia{

    @Override
    public boolean strategiaDiGioco(Mano m) {
        return m.cartaPescata().getValore() < 4;
    }

    public int quotaComputer(int piatto,int numPuntate){
        if (numPuntate==0)
            return 10;
        else
            return piatto/numPuntate;
    }
}
