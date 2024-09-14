module it.uniparthenope.sette_e_mezzo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jconsole;
    requires javafx.media;
    requires java.logging;
    requires javafx.graphics;


    opens it.uniparthenope.sette_e_mezzo to javafx.fxml;
    exports it.uniparthenope.sette_e_mezzo.observerPattern;
    opens it.uniparthenope.sette_e_mezzo.observerPattern to javafx.fxml;
    exports it.uniparthenope.sette_e_mezzo.game;
    opens it.uniparthenope.sette_e_mezzo.game to javafx.fxml;
    exports it.uniparthenope.sette_e_mezzo.strategyPattern;
    opens it.uniparthenope.sette_e_mezzo.strategyPattern to javafx.fxml;
    exports it.uniparthenope.sette_e_mezzo;
    exports it.uniparthenope.sette_e_mezzo.UI;
    opens it.uniparthenope.sette_e_mezzo.UI to javafx.fxml;
    exports it.uniparthenope.sette_e_mezzo.memento;
    opens it.uniparthenope.sette_e_mezzo.memento to javafx.fxml;
    exports it.uniparthenope.sette_e_mezzo.UI.StatePattern;
    opens it.uniparthenope.sette_e_mezzo.UI.StatePattern to javafx.fxml;
}