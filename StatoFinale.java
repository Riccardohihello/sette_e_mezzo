public class StatoFinale implements StatoGioco {

    public StatoFinale(int punteggioDealer, int punteggioGiocatore) {
    }

    public void distribuisciCarta() {
        // Non fa nulla, il gioco è finito
    }

    public void chiediCarta() {
        // Non fa nulla, il gioco è finito
    }

    public void fermati() {
        // Non fa nulla, il gioco è finito
    }

    public int getPunteggioMazziere() {
        return 10;
    }

    public int getPunteggioGiocatore() {
        return 2;
    }

    public boolean GiocoFinito() {
        return true;
    }
}