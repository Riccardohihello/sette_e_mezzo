package it.uniparthenope.programmazione3.model;

public class ImpostazioniPartita {
    private int giocatoriSelezionati;
    private int turniSelezionati;

    private static ImpostazioniPartita instance;

    // Costruttore privato per impedire istanze multiple
    private ImpostazioniPartita() {
        // Inizializza eventuali valori predefiniti
        giocatoriSelezionati = 2;
        turniSelezionati = 1;
    }

    // Metodo statico per ottenere l'istanza unica della classe
    public static ImpostazioniPartita getInstance() {
        if (instance == null) {
            synchronized (ImpostazioniPartita.class) {
                if (instance == null) {
                    instance = new ImpostazioniPartita();
                }
            }
        }
        return instance;
    }

    // Metodi getter e setter per i valori
    public int getGiocatoriSelezionati() {
        return giocatoriSelezionati;
    }

    public void aggiornaImpostazioni(int giocatoriSelezionati, int turniSelezionati) {
        this.giocatoriSelezionati = giocatoriSelezionati;
        this.turniSelezionati = turniSelezionati;
    }

    public int getTurniSelezionati() {
        return turniSelezionati;
    }
}
