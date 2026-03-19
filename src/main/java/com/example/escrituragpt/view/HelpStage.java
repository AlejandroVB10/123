package com.example.escrituragpt.view;

import com.example.escrituragpt.service.AudioManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Utility class responsible for loading and displaying the help screen.
 * <p>
 * The help screen explains the game rules and controls in detail,
 * providing context-sensitive assistance to the player
 * (<em>Help and documentation</em> — Nielsen heuristic #10).
 * </p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public class HelpStage {

    /** Path to the help FXML resource within the classpath. */
    private static final String FXML_PATH = "/com/example/escrituragpt/help-view.fxml";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private HelpStage() {
        throw new UnsupportedOperationException("HelpStage is a utility class.");
    }

    /**
     * Loads the help FXML and sets it as the scene on the given {@link Stage}.
     *
     * @param stage the {@link Stage} on which to show the help scene; must not be {@code null}
     * @throws IOException if the FXML resource cannot be loaded
     */
    public static void show(Stage stage) throws IOException {
        URL resource = HelpStage.class.getResource(FXML_PATH);
        if (resource == null) {
            throw new IOException("Cannot find FXML resource: " + FXML_PATH);
        }
        FXMLLoader loader = new FXMLLoader(resource);
        Scene scene = new Scene(loader.load(), 900, 620);
        stage.setTitle("Escritura Rápida — Ayuda");
        stage.setScene(scene);
        stage.show();

        // Play background music for the help screen
        AudioManager.getInstance().playMusic("help", "help-theme.mp3");
    }
}
