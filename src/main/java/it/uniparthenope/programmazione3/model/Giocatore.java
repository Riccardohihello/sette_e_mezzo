package it.uniparthenope.programmazione3.model;

import it.uniparthenope.programmazione3.Costanti;

public class Giocatore extends GiocatoreAbstract {

    public Giocatore(String nome) {
        this.nome = nome;
        this.gettoni = Costanti.m;
        this.mano = new Mano();
    }
    public int puntataDaVersare(int puntataDaVersare) {
        this.gettoni = this.gettoni - puntataDaVersare;
        if (this.gettoni - puntataDaVersare < 0) {
            throw new IllegalArgumentException("La puntata da versare è troppo alta per il saldo attuale.");
        } else {
            return puntataDaVersare;
        }
    }

}