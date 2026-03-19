package com.example.escrituragpt.controller;

import com.example.escrituragpt.view.MenuStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the help screen ({@code help-view.fxml}).
 * <p>
 * Displays detailed game rules, controls and tips.
 * Provides a "Volver" button to return to the main menu.
 * </p>
 *
 * <p>Implements Nielsen heuristic #10 — <em>Help and documentation</em>:
 * the system provides clear, accessible help that explains game mechanics
 * without requiring the player to memorise information.</p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public class HelpController {

    @FXML private Button backButton;

    /**
     * Handles the "Volver" button click event.
     * Navigates back to the main menu.
     *
     * @param event the {@link ActionEvent} fired by the back button
     */
    @FXML
    private void onBackButtonClicked(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        try {
            MenuStage.show(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
