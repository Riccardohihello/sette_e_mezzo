package it.uniparthenope.programmazione3;

public class StrategiaComputer implements Strategia{

    @Override
    public boolean strategiaDiGioco(Mano m) {
//        while (m.getValore()<5)
            if(m.cartaPescata().getValore()<4){
                return true;
            } else {
                return false;
            }
    }
}
