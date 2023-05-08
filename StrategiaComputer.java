public class StrategiaComputer implements Strategia{

    @Override
    public String strategiaDiGioco(Mano m) {
        if(m.cartaPescata().getValore()<6){
            return "Stai";
        } else {
            return "prendi un'altra carta";
        }
    }
}
