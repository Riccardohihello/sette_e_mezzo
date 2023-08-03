package it.uniparthenope.programmazione3;

class StatoAssegnaVincite implements StatoTurno {
    @Override
    public void raccoltaQuote(Turno turno) {
        // Non fare niente in questo stato
    }

    @Override
    public void mischiaMazzo(Turno turno) {
        // Non fare niente in questo stato
    }

    @Override
    public void svolgiMatch(Turno turno) {
        // Non fare niente in questo stato
    }

    @Override
    public void assegnaVincite(Turno turno) {
        // Implementazione della logica per l'assegnazione delle vincite ai vincitori
        for (Giocatore vincitore : turno.vincitori.getVincitori()) {
            int vincita = turno.piatto / turno.vincitori.numeroVincitori();
            vincitore.aumentaGettoni(vincita);
        }
    }

    @Override
    public void stampaRisultati(Turno turno) {
        // Stampa i risultati del turno, ad esempio i gettoni dei giocatori dopo l'assegnazione delle vincite
        for (Giocatore giocatore : turno.giocatori) {
            System.out.println(giocatore.getNome() + " ha ora " + giocatore.gettoni + " gettoni.");
        }
    }
}