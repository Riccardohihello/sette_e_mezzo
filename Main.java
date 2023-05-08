public class Main {

        public static void main(String[] args){
               Mano mano = new Mano();
               Carta carta = new Carta(1, "coppe");
               mano.addCarta(carta);
               System.out.println(mano.cartaPescata().getValore()+" "+ mano.cartaPescata().getSeme());
        }
        
}
