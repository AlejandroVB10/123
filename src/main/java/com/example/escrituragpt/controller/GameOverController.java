package com.example.escrituragpt.controller;

import com.example.escrituragpt.model.ScoreRecord;
import com.example.escrituragpt.view.MenuStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller for the game-over screen ({@code gameover-view.fxml}).
 * <p>
 * Displays the player's final statistics (levels completed, time remaining
 * on the last level) and provides a "Reiniciar" button to return to the
 * main menu and start a new session.
 * </p>
 *
 * <p>The score data is injected via {@link #setScore(ScoreRecord)} after
 * the FXML is loaded, so the labels are populated programmatically rather
 * than through FXML bindings.</p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public class GameOverController {

    @FXML private Text levelsCompletedLabel;
    @FXML private Text timeRemainingLabel;
    @FXML private Button restartButton;

    /**
     * Populates the game-over screen with the player's final score.
     * <p>
     * Must be called after the FXML is loaded and before the scene is shown.
     * </p>
     *
     * @param score the {@link ScoreRecord} containing end-of-game statistics;
     *              must not be {@code null}
     */
    public void setScore(ScoreRecord score) {
        levelsCompletedLabel.setText(String.valueOf(score.getLevelsCompleted()));
        if (score.getTimeRemainingOnLastLevel() > 0) {
            timeRemainingLabel.setText(score.getTimeRemainingOnLastLevel() + " segundos restantes");
        } else {
            timeRemainingLabel.setText("Tiempo agotado en el último nivel");
        }
    }

    /**
     * Handles the "Reiniciar" button click event.
     * <p>
     * Navigates back to the main menu screen so the player can start a fresh game.
     * </p>
     *
     * @param event the {@link ActionEvent} fired by the restart button
     */
    @FXML
    private void onRestartButtonClicked(ActionEvent event) {
        Stage stage = (Stage) restartButton.getScene().getWindow();
        try {
            MenuStage.show(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
