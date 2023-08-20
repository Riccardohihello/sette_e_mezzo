module it.uniparthenope.programmazione3 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens it.uniparthenope.programmazione3 to javafx.fxml;
    exports it.uniparthenope.programmazione3.observerPattern;
    opens it.uniparthenope.programmazione3.observerPattern to javafx.fxml;
    exports it.uniparthenope.programmazione3.statePattern;
    opens it.uniparthenope.programmazione3.statePattern to javafx.fxml;
    exports it.uniparthenope.programmazione3.controllers;
    opens it.uniparthenope.programmazione3.controllers to javafx.fxml;
    exports it.uniparthenope.programmazione3.partita;
    opens it.uniparthenope.programmazione3.partita to javafx.fxml;
    exports it.uniparthenope.programmazione3.strategyPattern;
    opens it.uniparthenope.programmazione3.strategyPattern to javafx.fxml;
    exports it.uniparthenope.programmazione3;
}