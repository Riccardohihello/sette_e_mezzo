package it.uniparthenope.programmazione3.model;

import it.uniparthenope.programmazione3.strategyPattern.StrategiaGiocatore;

public class Giocatore extends GiocatoreAbstract {

    public Giocatore(String nome) {
        this.strat = new StrategiaGiocatore();
        this.nome = nome;
        this.gettoni = Costanti.m;
        this.mano = new Mano();
        this.stato = "In Attesa..";
    }
    public void puntataDaVersare(int puntataDaVersare) {
        if (this.gettoni - puntataDaVersare < 0) {
            throw new IllegalArgumentException("La puntata da versare è troppo alta per il saldo attuale.");
        } else {
            this.gettoni = this.gettoni - puntataDaVersare;
        }
    }

}