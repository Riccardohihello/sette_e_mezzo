package it.uniparthenope.programmazione3;

interface StatoTurno {
    void raccoltaQuote(Turno turno);
    void mischiaMazzo(Turno turno);
    void svolgiMatch(Turno turno);
    void assegnaVincite(Turno turno);
    void stampaRisultati(Turno turno);
}
