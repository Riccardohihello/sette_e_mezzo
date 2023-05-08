public class StatoIniziale implements StatoGioco {
    private int punteggioDealer;
    private int punteggioGiocatore;
    private Mazzo mazzo;
    public StatoIniziale() {
        punteggioDealer = 0;
        punteggioGiocatore = 0;
        mazzo = Mazzo.creaMazzo();
        mazzo.mischia();
    }
    public void distribuisciCarta() {
        // Distribuisce una carta al giocatore
        punteggioGiocatore += mazzo.pesca().getValore();
        // Passa al prossimo stato
        }

public void chiediCarta() {
        // Distribuisce una carta al giocatore
        punteggioGiocatore += mazzo.pesca().getValore();
        // Passa al prossimo stato
        }

public void fermati() {
        // Passa al prossimo stato
        }

public int getPunteggioDealer() {
        return punteggioDealer;
        }

public int getPunteggioGiocatore() {
        return punteggioGiocatore;
        }

public boolean GiocoFinito() {
        return false;
        }
}