package it.uniparthenope.programmazione3.UI.playerStates;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;

public interface PlayerState {
    void updateState(PlayerUI playerUI, Giocatore player);
}