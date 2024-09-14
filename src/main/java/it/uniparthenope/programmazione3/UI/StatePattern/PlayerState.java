package it.uniparthenope.programmazione3.UI.StatePattern;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;

public interface PlayerState {
    void updateState(PlayerGameCell playerGameCell, Giocatore player);
}