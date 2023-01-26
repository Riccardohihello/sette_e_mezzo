class Partita {
    private List<Giocatore> giocatori;
    private Mazzo mazzo;
    private Mazziere mazziere;

    public Partita() {
        giocatori = new ArrayList<Giocatore>();
    }


    public void assegnaMazziere() {
        mazziere = giocatori.get(indiceMazziere);
        indiceMazziere = indiceMazziere % giocatori.size();
        indiceMazziere += 1
    }
}