module it.uniparthenope.programmazione3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jconsole;
    requires javafx.media;
    requires java.logging;


    opens it.uniparthenope.programmazione3 to javafx.fxml;
    exports it.uniparthenope.programmazione3.observerPattern;
    opens it.uniparthenope.programmazione3.observerPattern to javafx.fxml;
    exports it.uniparthenope.programmazione3.game;
    opens it.uniparthenope.programmazione3.game to javafx.fxml;
    exports it.uniparthenope.programmazione3.strategyPattern;
    opens it.uniparthenope.programmazione3.strategyPattern to javafx.fxml;
    exports it.uniparthenope.programmazione3;
    exports it.uniparthenope.programmazione3.UI;
    opens it.uniparthenope.programmazione3.UI to javafx.fxml;
    exports it.uniparthenope.programmazione3.memento;
    opens it.uniparthenope.programmazione3.memento to javafx.fxml;
    exports it.uniparthenope.programmazione3.UI.playerStates;
    opens it.uniparthenope.programmazione3.UI.playerStates to javafx.fxml;
}