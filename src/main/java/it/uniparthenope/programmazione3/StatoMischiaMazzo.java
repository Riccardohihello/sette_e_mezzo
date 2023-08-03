package it.uniparthenope.programmazione3;

class StatoMischiaMazzo implements StatoTurno {
    @Override
    public void raccoltaQuote(Turno turno) {
        // Non fa niente in questo stato
    }

    @Override
    public void mischiaMazzo(Turno turno) {
        turno.mazzo.mischia();
        System.out.println("mazzo mischiato");

        // Passa allo stato successivo: svolgimento del match
        turno.setStatoTurno(new StatoSvolgiMatch());
    }

    @Override
    public void svolgiMatch(Turno turno) {}

    @Override
    public void assegnaVincite(Turno turno) {}

    @Override
    public void stampaRisultati(Turno turno) {}
}
