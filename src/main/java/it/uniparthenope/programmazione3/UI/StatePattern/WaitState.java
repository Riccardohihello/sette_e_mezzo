package it.uniparthenope.programmazione3.UI.StatePattern;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;

public class WaitState implements PlayerState {
    @Override
    public void updateState(PlayerGameCell playerGameCell, Giocatore player) {
        playerGameCell.stateLabel.setText("In attesa...");
        playerGameCell.setBorderColor(PlayerGameCell.DEFAULT_BACKGROUND_COLOR);
        playerGameCell.setTextStyle(playerGameCell.stateLabel, PlayerGameCell.DEFAULT_TEXT_COLOR);
    }

}