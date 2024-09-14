package it.uniparthenope.programmazione3.UI.StatePattern;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;

public class MatchState implements PlayerState {
    @Override
    public void updateState(PlayerGameCell playerGameCell, Giocatore player) {
        playerGameCell.stateLabel.setText("Valore mano: " + player.getMano().getValore());
        playerGameCell.setBorderColor(PlayerGameCell.BORDER_GREEN);
        playerGameCell.setTextStyle(playerGameCell.stateLabel, PlayerGameCell.BORDER_GREEN);
    }
}