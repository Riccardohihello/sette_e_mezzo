package it.uniparthenope.programmazione3.UI.playerStates;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;

public class BustedState implements PlayerState {
    @Override
    public void updateState(PlayerUI playerUI, Giocatore player) {
        playerUI.stateLabel.setText("Sballato!");
        playerUI.setBorderColor(PlayerUI.BORDER_RED);
        playerUI.setTextStyle(playerUI.stateLabel, PlayerUI.BORDER_RED);
    }
}
