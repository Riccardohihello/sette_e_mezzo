package it.uniparthenope.programmazione3.model;

public class Errore extends Exception {

        Errore()
        {
            super("Attenzione, stai riferendo una stringa non inizializzata");
        }
}

