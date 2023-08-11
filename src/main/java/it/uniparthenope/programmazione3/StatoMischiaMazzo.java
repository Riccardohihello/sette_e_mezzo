package it.uniparthenope.programmazione3;

class StatoMischiaMazzo implements StatoTurno {
@Override
public void eseguiAzione(Turno turno) {
        //turno.getMazzo().mischia();
        System.out.println("Mazzo mischiato");

        // Passa allo stato successivo: svolgimento del match
        turno.setStatoTurno(new StatoSvolgiMatch());
        }
}