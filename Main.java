
public class Main {



        public static void main(String[] args){
                Mazzo mazzoo = Mazzo.creaMazzo();
                mazzoo.rimuovi();
                mazzoo.stampa();
                Giocatore giocatore1 = new Giocatore("Giulio");
                mazzoo.mescola();

                Mazziere mazziere = new Mazziere(giocatore1.getNome(), mazzoo);
                System.out.println(mazziere.getNome());
                mazziere.getMazzo();

        }
        
}
