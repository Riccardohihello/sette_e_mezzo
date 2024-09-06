package it.uniparthenope.programmazione3.memento;

import it.uniparthenope.programmazione3.game.SettingsSingleton;

import java.io.Serializable;

public class Memento implements Serializable {

    private SettingsSingleton settings;
    public Memento(SettingsSingleton settings) {
        this.settings = settings;
    }
    public void setSettings(SettingsSingleton settings) {this.settings = settings;}
    public SettingsSingleton getSettings() {
        return settings;
    }
}

