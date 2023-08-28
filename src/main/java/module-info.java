module it.uniparthenope.programmazione3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jconsole;


    opens it.uniparthenope.programmazione3 to javafx.fxml;
    exports it.uniparthenope.programmazione3.observerPattern;
    opens it.uniparthenope.programmazione3.observerPattern to javafx.fxml;
    exports it.uniparthenope.programmazione3.statePattern;
    opens it.uniparthenope.programmazione3.statePattern to javafx.fxml;
    exports it.uniparthenope.programmazione3.controllers;
    opens it.uniparthenope.programmazione3.controllers to javafx.fxml;
    exports it.uniparthenope.programmazione3.model;
    opens it.uniparthenope.programmazione3.model to javafx.fxml;
    exports it.uniparthenope.programmazione3.strategyPattern;
    opens it.uniparthenope.programmazione3.strategyPattern to javafx.fxml;
    exports it.uniparthenope.programmazione3;
    exports it.uniparthenope.programmazione3.UI;
    opens it.uniparthenope.programmazione3.UI to javafx.fxml;
}