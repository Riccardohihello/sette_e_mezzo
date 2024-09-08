package it.uniparthenope.programmazione3.UI;

import it.uniparthenope.programmazione3.strategyPattern.Giocatore;
import javafx.scene.control.ListView;

public class StatsUI {
    public ListView<Giocatore> Stats;

    public void initialize(){
        Stats.setCellFactory(listView -> new PlayerUI());
    }
}
