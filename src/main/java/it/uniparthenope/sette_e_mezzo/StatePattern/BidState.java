package it.uniparthenope.sette_e_mezzo.StatePattern;

import it.uniparthenope.sette_e_mezzo.strategyPattern.Giocatore;

public class BidState implements PlayerState {
    @Override
    public void updateState(PlayerGameCell playerGameCell, Giocatore player) {
        playerGameCell.stateLabel.setText("Deve versare");
        playerGameCell.setBorderColor(PlayerGameCell.BORDER_GREEN);
        playerGameCell.setTextStyle(playerGameCell.stateLabel, PlayerGameCell.BORDER_GREEN);
    }
}