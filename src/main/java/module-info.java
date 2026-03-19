module com.example.escrituragpt {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.escrituragpt to javafx.fxml;
    opens com.example.escrituragpt.controller to javafx.fxml;
    opens com.example.escrituragpt.model to javafx.fxml;
    opens com.example.escrituragpt.model.game to javafx.fxml;
    opens com.example.escrituragpt.model.words to javafx.fxml;
    opens com.example.escrituragpt.view to javafx.fxml;
    opens com.example.escrituragpt.interfaces to javafx.fxml;
    opens com.example.escrituragpt.service to javafx.fxml;

    exports com.example.escrituragpt;
    exports com.example.escrituragpt.controller;
    exports com.example.escrituragpt.model;
    exports com.example.escrituragpt.model.game;
    exports com.example.escrituragpt.model.words;
    exports com.example.escrituragpt.view;
    exports com.example.escrituragpt.interfaces;
    exports com.example.escrituragpt.service;
}