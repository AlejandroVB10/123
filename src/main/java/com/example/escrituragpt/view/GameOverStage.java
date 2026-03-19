package com.example.escrituragpt.view;

import com.example.escrituragpt.controller.GameOverController;
import com.example.escrituragpt.model.ScoreRecord;
import com.example.escrituragpt.service.AudioManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Utility class responsible for loading and displaying the game-over screen.
 * <p>
 * Accepts a {@link ScoreRecord} and passes it to the {@link GameOverController}
 * after the FXML is loaded, so the final statistics are shown correctly.
 * </p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public class GameOverStage {

    /** Path to the game-over FXML resource within the classpath. */
    private static final String FXML_PATH = "/com/example/escrituragpt/gameover-view.fxml";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private GameOverStage() {
        throw new UnsupportedOperationException("GameOverStage is a utility class.");
    }

    /**
     * Loads the game-over FXML, injects the score into its controller, and
     * sets the resulting scene on the given {@link Stage}.
     *
     * @param stage the {@link Stage} on which to show the game-over scene; must not be {@code null}
     * @param score the {@link ScoreRecord} to display; must not be {@code null}
     * @throws IOException if the FXML resource cannot be loaded
     */
    public static void show(Stage stage, ScoreRecord score) throws IOException {
        URL resource = GameOverStage.class.getResource(FXML_PATH);
        if (resource == null) {
            throw new IOException("Cannot find FXML resource: " + FXML_PATH);
        }
        FXMLLoader loader = new FXMLLoader(resource);
        Scene scene = new Scene(loader.load(), 900, 620);

        GameOverController controller = loader.getController();
        controller.setScore(score);

        stage.setTitle("Escritura Rápida — Fin del juego");
        stage.setScene(scene);
        stage.show();

        // Play background music for the game-over screen
        AudioManager.getInstance().playMusic("gameOver", "gameover-theme.mp3");
    }
}
