package it.uniparthenope.programmazione3;

public class Mazziere extends Persona {

    public Mazziere(Giocatore g) {
        this.nome = g.getNome();

        System.out.println("\nsrc.Mazziere " + this.nome + " creato");
    }
}