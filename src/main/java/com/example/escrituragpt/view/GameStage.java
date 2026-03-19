package com.example.escrituragpt.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Utility class responsible for loading and displaying the main game screen.
 * <p>
 * Encapsulates the FXML loading and scene switching logic so that controllers
 * do not need to know the exact resource path.
 * </p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public class GameStage {

    /** Path to the game FXML resource within the classpath. */
    private static final String FXML_PATH = "/com/example/escrituragpt/game-view.fxml";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private GameStage() {
        throw new UnsupportedOperationException("GameStage is a utility class.");
    }

    /**
     * Loads the game FXML and sets it as the scene on the given {@link Stage}.
     *
     * @param stage the {@link Stage} on which to show the game scene; must not be {@code null}
     * @throws IOException if the FXML resource cannot be loaded
     */
    public static void show(Stage stage) throws IOException {
        URL resource = GameStage.class.getResource(FXML_PATH);
        if (resource == null) {
            throw new IOException("Cannot find FXML resource: " + FXML_PATH);
        }
        FXMLLoader loader = new FXMLLoader(resource);
        Scene scene = new Scene(loader.load(), 900, 620);
        stage.setTitle("Escritura Rápida — ¡Juega!");
        stage.setScene(scene);
        stage.show();
    }
}
