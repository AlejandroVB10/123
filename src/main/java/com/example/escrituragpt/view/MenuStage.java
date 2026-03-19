package com.example.escrituragpt.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Utility class responsible for loading and displaying the main menu screen.
 * <p>
 * Encapsulates the FXML loading and scene switching logic so that controllers
 * do not need to know where the FXML resource is located.
 * </p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public class MenuStage {

    /** Path to the menu FXML resource within the classpath. */
    private static final String FXML_PATH = "/com/example/escrituragpt/menu-view.fxml";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private MenuStage() {
        throw new UnsupportedOperationException("MenuStage is a utility class.");
    }

    /**
     * Loads the menu FXML and sets it as the scene on the given {@link Stage}.
     *
     * @param stage the {@link Stage} on which to show the menu scene; must not be {@code null}
     * @throws IOException if the FXML resource cannot be loaded
     */
    public static void show(Stage stage) throws IOException {
        URL resource = MenuStage.class.getResource(FXML_PATH);
        if (resource == null) {
            throw new IOException("Cannot find FXML resource: " + FXML_PATH);
        }
        FXMLLoader loader = new FXMLLoader(resource);
        Scene scene = new Scene(loader.load(), 900, 620);
        stage.setTitle("Escritura Rápida");
        stage.setScene(scene);
        stage.show();
    }
}
