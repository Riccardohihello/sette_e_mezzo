package it.uniparthenope.programmazione3.UI.playerStates;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;

public class MatchState implements PlayerState {
    @Override
    public void updateState(PlayerUI playerUI, Giocatore player) {
        playerUI.stateLabel.setText("Valore mano: " + player.getMano().getValore());
        playerUI.setBorderColor(PlayerUI.BORDER_GREEN);
        playerUI.setTextStyle(playerUI.stateLabel, PlayerUI.BORDER_GREEN);
    }
}