package it.uniparthenope.sette_e_mezzo.StatePattern;

import it.uniparthenope.sette_e_mezzo.strategyPattern.Giocatore;

public class BiddedState implements PlayerState {
    @Override
    public void updateState(PlayerGameCell playerGameCell, Giocatore player) {
        playerGameCell.stateLabel.setText("Versato...");
        playerGameCell.setBorderColor(PlayerGameCell.DEFAULT_BACKGROUND_COLOR);
        playerGameCell.setTextStyle(playerGameCell.stateLabel, PlayerGameCell.DEFAULT_TEXT_COLOR);
    }
}
