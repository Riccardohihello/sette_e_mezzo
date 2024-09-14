package it.uniparthenope.programmazione3.UI.StatePattern;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;

public class ResultsState implements PlayerState {
    @Override
    public void updateState(PlayerGameCell playerGameCell, Giocatore player) {
        playerGameCell.stateLabel.setText("Valore mano: " + player.getMano().getValore());
        playerGameCell.balanceLabel.setVisible(true);
        playerGameCell.stateLabel.setVisible(true);
        playerGameCell.setTextStyle(playerGameCell.stateLabel, PlayerGameCell.DEFAULT_TEXT_COLOR);
    }
}