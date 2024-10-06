package it.uniparthenope.sette_e_mezzo.StatePattern;

import it.uniparthenope.sette_e_mezzo.strategyPattern.Giocatore;

public class MatchState implements PlayerState {
    @Override
    public void updateState(PlayerGameCell playerGameCell, Giocatore player) {
        playerGameCell.stateLabel.setText("Valore mano: " + player.getMano().getValore());
        playerGameCell.setBorderColor(PlayerGameCell.BORDER_GREEN);
        playerGameCell.setTextStyle(playerGameCell.stateLabel, PlayerGameCell.BORDER_GREEN);
    }
}