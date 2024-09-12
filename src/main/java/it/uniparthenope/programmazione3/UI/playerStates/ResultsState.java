package it.uniparthenope.programmazione3.UI.playerStates;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;

public class ResultsState implements PlayerState {
    @Override
    public void updateState(PlayerUI playerUI, Giocatore player) {
        playerUI.stateLabel.setText("Valore mano: " + player.getMano().getValore());
        playerUI.balanceLabel.setVisible(true);
        playerUI.stateLabel.setVisible(true);
        playerUI.setTextStyle(playerUI.stateLabel, PlayerUI.DEFAULT_TEXT_COLOR);
    }
}