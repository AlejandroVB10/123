package com.example.escrituragpt.controller;

import com.example.escrituragpt.view.GameStage;
import com.example.escrituragpt.view.HelpStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the main menu screen ({@code menu-view.fxml}).
 * <p>
 * Handles the "Jugar" button click event, which transitions the application
 * from the main menu to the game screen by delegating to {@link GameStage}.
 * </p>
 * <p>
 * Handles the "Ayuda" button click event, which transitions the application
 * from the main menu to the help screen by delegating to {@link HelpStage}.
 * </p>
 *
 * <p>Heuristic applied — <em>Recognition rather than recall</em>: the menu
 * presents clear, labelled actions so the player does not need to remember
 * how to start the game.</p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public class MenuController {

    @FXML
    private Button playButton;

    @FXML
    private Button helpButton;

    /**
     * Initializes the menu controller.
     * Called automatically by the JavaFX FXMLLoader after FXML injection.
     */
    @FXML
    private void initialize() {
        // No additional initialization required for the menu screen.
    }

    /**
     * Handles the "Jugar" button click event.
     * <p>
     * Retrieves the current {@link Stage} from the button's scene graph
     * and delegates to {@link GameStage#show(Stage)} to replace the current
     * scene with the game screen.
     * </p>
     *
     * @param event the {@link ActionEvent} fired by the "Jugar" button
     */
    @FXML
    private void onPlayButtonClicked(ActionEvent event) {
        Stage stage = (Stage) playButton.getScene().getWindow();
        try {
            GameStage.show(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the "Ayuda" button click event.
     * <p>
     * Retrieves the current {@link Stage} from the button's scene graph
     * and delegates to {@link HelpStage#show(Stage)} to replace the current
     * scene with the help screen.
     * </p>
     *
     * @param event the {@link ActionEvent} fired by the "Ayuda" button
     */
    @FXML
    private void onHelpButtonClicked(ActionEvent event) {
        Stage stage = (Stage) helpButton.getScene().getWindow();
        try {
            HelpStage.show(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
