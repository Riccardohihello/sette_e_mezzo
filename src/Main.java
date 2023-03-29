package src;


public class Main {



        public static void main(String[] args){
                Carta cartaa = new Carta(10,"coppe");
                System.out.println(cartaa.getSeme());
                Mazzo mazzo = new Mazzo();
                mazzo.stampa();
                mazzo.mescola();
                System.out.println("Mazzo Mescolato:");
                mazzo.stampa();
        }
        
}
