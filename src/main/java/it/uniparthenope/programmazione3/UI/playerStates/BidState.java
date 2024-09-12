package it.uniparthenope.programmazione3.UI.playerStates;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;

public class BidState implements PlayerState {
    @Override
    public void updateState(PlayerUI playerUI, Giocatore player) {
        playerUI.stateLabel.setText("Deve versare");
        playerUI.setBorderColor(PlayerUI.BORDER_GREEN);
        playerUI.setTextStyle(playerUI.stateLabel, PlayerUI.BORDER_GREEN);
    }
}