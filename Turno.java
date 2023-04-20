import java.util.ArrayList;

class Turno {

    public Turno(Mazzo m,int imazziere) {
        String[] nomi = {"Pippo","Pluto","Paperino","Topolino"};
        ArrayList<Giocatore> giocatori = new ArrayList<>(); //creazione dei giocatori

        for(int i = 0; i < Costanti.n; i++)
            giocatori.add(new Giocatore(nomi[i]));
        for (Giocatore giocatore : giocatori) {
            System.out.print(giocatore.getNome()+", ");
        }

        new Mazziere(giocatori.get(imazziere));
    }

}
