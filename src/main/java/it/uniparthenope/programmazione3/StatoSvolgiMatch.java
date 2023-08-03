package it.uniparthenope.programmazione3;

import java.util.Objects;

class StatoSvolgiMatch implements StatoTurno {
    @Override
    public void raccoltaQuote(Turno turno) {
        // Non fare niente in questo stato
    }

    @Override
    public void mischiaMazzo(Turno turno) {
        // Non fare niente in questo stato
    }

    @Override
    public void svolgiMatch(Turno turno) {
        System.out.println("svolgi match");
        double manoM = turno.mazziere.sfida(turno.mazzo);
        System.out.println("sono il mazziere con: " + manoM);
        for (Giocatore g : turno.giocatori) {
            if (!Objects.equals(g.getNome(), turno.mazziere.getNome())) {
                if (g.sfida(turno.mazzo) > manoM && g.sfida(turno.mazzo) < 7.5) {
                    turno.vincitori.registraVincitori(g);
                    System.out.println(g.getNome() + " ha battuto il mazziere");
                } else {
                    System.out.println(g.getNome() + " ha perso e il val della mano è " + g.getMano().getValore());
                }
            } else if (manoM == 7.5) {
                System.out.println(turno.mazziere.getNome() + "ha come valore: " + turno.mazziere.getMano().getValore());
            } else if (manoM > 7.5) {
                System.out.println(turno.mazziere.getNome() + "ha sballato con valore: " + turno.mazziere.getMano().getValore());
            }
        }

        // Passa allo stato successivo: assegnazione delle vincite
        turno.setStatoTurno(new StatoAssegnaVincite());
    }

    // Implementazione degli altri metodi, se necessario (potrebbero essere vuoti)
    @Override
    public void assegnaVincite(Turno turno) {}

    @Override
    public void stampaRisultati(Turno turno) {}
}
