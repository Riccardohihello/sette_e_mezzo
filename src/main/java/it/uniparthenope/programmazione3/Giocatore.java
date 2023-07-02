package it.uniparthenope.programmazione3;

public class Giocatore extends GiocatoreAbstract {

    public Giocatore(String nome) {
        this.nome = nome;
        this.gettoni = Costanti.m;
        this.mano = new Mano();
    }



}