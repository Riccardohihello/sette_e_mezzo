package it.uniparthenope.programmazione3.classes;

import java.util.ArrayList;

public class RegistroVincite {
        private static RegistroVincite instance;
        private final ArrayList<Giocatore> vincitori;

        private RegistroVincite() {
            vincitori = new ArrayList<>();
        }

        public static RegistroVincite getInstance() {
            if (instance == null) {
                instance = new RegistroVincite();
            }
            return instance;
        }

        public void registraVincitori(Giocatore giocatore) {
            vincitori.add(giocatore);
        }

        public void pagaVincite(Mazziere mazziere, int quota) {
            for (Giocatore g : vincitori) {
                    mazziere.pagaVincite(quota * 2, g);
            }
        }

        public void stampaVincitori() {
            for (Giocatore giocatore : vincitori) {
                System.out.println(giocatore.getNome()+" ha "+ giocatore.getGettoni());
            }
        }
        public void reset() {
            vincitori.clear();
            instance = null;
        }

    public int numeroVincitori() {
            return vincitori.size();
    }

    public ArrayList<Giocatore> getVincitori() {
            return vincitori;
    }
}


