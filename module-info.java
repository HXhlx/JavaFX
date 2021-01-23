module OfficeManage {
    requires javafx.controls;
    requires javafx.fxml;
    opens sample.controller to javafx.fxml;
    opens sample.model to javafx.fxml;
    opens sample.view to javafx.fxml;
    exports sample.controller;
    exports sample.model;
}