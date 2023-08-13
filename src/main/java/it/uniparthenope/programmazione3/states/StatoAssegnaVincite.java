package it.uniparthenope.programmazione3.states;

import it.uniparthenope.programmazione3.classes.Giocatore;
import it.uniparthenope.programmazione3.Turno;
import it.uniparthenope.programmazione3.interfaces.StatoTurno;

public class StatoAssegnaVincite implements StatoTurno {
    @Override
    public void eseguiAzione(Turno turno) {
        assegnaVincite(turno);
        stampaRisultati(turno);
    }

    private void assegnaVincite(Turno turno) {
        for (Giocatore vincitore : turno.getVincitori()) {
            int vincita = calcolaVincita(turno, vincitore);
            vincitore.aumentaGettoni(vincita);
        }
    }

    private int calcolaVincita(Turno turno, Giocatore vincitore) {
        return turno.piatto / turno.getNumeroVincitori();
    }

    private void stampaRisultati(Turno turno) {
        for (Giocatore giocatore : turno.getVincitori()) {
            System.out.println(giocatore.getNome() + " ha ora " + giocatore.getGettoni() + " gettoni.");
        }
    }


}

