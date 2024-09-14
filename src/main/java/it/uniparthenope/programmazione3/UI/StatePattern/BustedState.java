package it.uniparthenope.programmazione3.UI.StatePattern;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;

public class BustedState implements PlayerState {
    @Override
    public void updateState(PlayerGameCell playerGameCell, Giocatore player) {
        playerGameCell.stateLabel.setText("Sballato!");
        playerGameCell.setBorderColor(PlayerGameCell.BORDER_RED);
        playerGameCell.setTextStyle(playerGameCell.stateLabel, PlayerGameCell.BORDER_RED);
    }
}
