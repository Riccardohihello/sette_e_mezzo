package it.uniparthenope.programmazione3.statePattern;

import it.uniparthenope.programmazione3.model.Giocatore;
import it.uniparthenope.programmazione3.model.Turno;

public class StatoAssegnaVincite implements StatoTurno {
    @Override
    public void eseguiAzione(Turno turno) {
        System.out.println("Finito");
    }


    private int calcolaVincita(Turno turno) {
        return turno.piatto / turno.getNumeroVincitori();
    }

    private void stampaRisultati(Turno turno) {
        for (Giocatore giocatore : turno.getVincitori()) {
            System.out.println(giocatore.getNome() + " ha ora " + giocatore.getGettoni() + " gettoni.");
        }
    }


}

