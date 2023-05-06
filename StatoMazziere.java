public class StatoMazziere implements StatoGioco {
    private int punteggioDealer;
    private int punteggioGiocatore;
    private Mazzo mazzo;

    public StatoMazziere(int punteggioDealer, int punteggioGiocatore, Mazzo mazzo) {
        this.punteggioDealer = punteggioDealer;
        this.punteggioGiocatore = punteggioGiocatore;
        this.mazzo = mazzo;
    }

    public void distribuisciCarta() {
        // Distribuisce una carta al giocatore
        punteggioGiocatore += mazzo.pesca().getValore();
    }

    public void chiediCarta() {
        // Distribuisce una carta al dealer
        punteggioDealer += mazzo.pesca().getValore();
    }

    public void fermati() {
        // Calcola il punteggio del dealer
        while (punteggioDealer < 5.5) {
            punteggioDealer += mazzo.pesca().getValore();
        }
    }

    public int getPunteggioMazziere() {
        return punteggioDealer;
    }

    public int getPunteggioGiocatore() {
        return punteggioGiocatore;
    }

    public boolean GiocoFinito() {
        return false;
    }
}