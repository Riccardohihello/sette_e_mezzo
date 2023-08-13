package it.uniparthenope.programmazione3.states;

import it.uniparthenope.programmazione3.Turno;

public abstract class AbstractState {
    protected Turno turno;

    public AbstractState(Turno turno) {
        this.turno = turno;
    }

    abstract void faseIniziale();
    abstract void sfida();
    abstract void faseVittoria();

}
