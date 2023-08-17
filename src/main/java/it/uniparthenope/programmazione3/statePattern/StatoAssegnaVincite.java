package it.uniparthenope.programmazione3.statePattern;

import it.uniparthenope.programmazione3.partita.Giocatore;
import it.uniparthenope.programmazione3.partita.Turno;

public class StatoAssegnaVincite implements StatoTurno {
    @Override
    public void eseguiAzione(Turno turno) {
        turno.setRegistroVincite();
        assegnaVincite(turno);
        stampaRisultati(turno);
        System.out.println("Finito");
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

