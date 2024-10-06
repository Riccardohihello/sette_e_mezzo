package it.uniparthenope.sette_e_mezzo.StatePattern;

import it.uniparthenope.sette_e_mezzo.strategyPattern.Giocatore;

public class ResultsState implements PlayerState {
    @Override
    public void updateState(PlayerGameCell playerGameCell, Giocatore player) {
        playerGameCell.stateLabel.setText("Valore mano: " + player.getMano().getValore());
        playerGameCell.balanceLabel.setVisible(true);
        playerGameCell.stateLabel.setVisible(true);
        playerGameCell.setTextStyle(playerGameCell.stateLabel, PlayerGameCell.DEFAULT_TEXT_COLOR);
    }
}