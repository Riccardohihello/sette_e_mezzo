module it.uniparthenope.programmazione3 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens it.uniparthenope.programmazione3 to javafx.fxml;
    exports it.uniparthenope.programmazione3;
    exports it.uniparthenope.programmazione3.interfaces;
    opens it.uniparthenope.programmazione3.interfaces to javafx.fxml;
    exports it.uniparthenope.programmazione3.states;
    opens it.uniparthenope.programmazione3.states to javafx.fxml;
    exports it.uniparthenope.programmazione3.controllers;
    opens it.uniparthenope.programmazione3.controllers to javafx.fxml;
    exports it.uniparthenope.programmazione3.classes;
    opens it.uniparthenope.programmazione3.classes to javafx.fxml;
}