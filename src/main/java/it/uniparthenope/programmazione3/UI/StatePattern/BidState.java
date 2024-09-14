package it.uniparthenope.programmazione3.UI.StatePattern;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;

public class BidState implements PlayerState {
    @Override
    public void updateState(PlayerGameCell playerGameCell, Giocatore player) {
        playerGameCell.stateLabel.setText("Deve versare");
        playerGameCell.setBorderColor(PlayerGameCell.BORDER_GREEN);
        playerGameCell.setTextStyle(playerGameCell.stateLabel, PlayerGameCell.BORDER_GREEN);
    }
}