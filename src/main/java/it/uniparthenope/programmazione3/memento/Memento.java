package it.uniparthenope.programmazione3.memento;

import java.io.Serializable;

public class Memento implements Serializable {

    private gameSettings settings;
    public Memento(gameSettings settings) {
        this.settings = settings;
    }
    public void setSettings(gameSettings settings) {this.settings = settings;}
    public gameSettings getSettings() {
        return settings;
    }
}

