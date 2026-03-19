package com.example.escrituragpt.controller;

import com.example.escrituragpt.interfaces.GameEventListener;
import com.example.escrituragpt.interfaces.KeyboardInputAdapter;
import com.example.escrituragpt.interfaces.MouseSubmitAdapter;
import com.example.escrituragpt.interfaces.TypingEventHandler;
import com.example.escrituragpt.model.ScoreRecord;
import com.example.escrituragpt.model.game.GameAdapter;
import com.example.escrituragpt.model.game.IGame;
import com.example.escrituragpt.model.words.WordAdapter;
import com.example.escrituragpt.view.GameOverStage;
import com.example.escrituragpt.view.MenuStage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main game controller for the Escritura Rápida game ({@code game-view.fxml}).
 * <p>
 * Implements {@link TypingEventHandler} and {@link GameEventListener} to handle
 * player input and game progression events. Uses:
 * </p>
 * <ul>
 *   <li>{@link KeyboardInputAdapter} — adapter for Enter key events on the text field</li>
 *   <li>{@link MouseSubmitAdapter}  — adapter for the "Validar" button click</li>
 *   <li>{@link GameTimerTask}       — inner class acting as the per-second tick handler</li>
 * </ul>
 *
 * <h3>UX Heuristics implemented</h3>
 * <ol>
 *   <li><strong>Visibility of system status</strong>: {@code labelTiempo} and {@code progressTiempo}
 *       update every second; colour turns warning when time is critical.</li>
 *   <li><strong>Error prevention</strong>: {@code btnValidar} is disabled when
 *       {@code campoRespuesta} is empty.</li>
 *   <li><strong>Feedback</strong>: {@code labelFeedback} and {@code iconFeedback} provide
 *       immediate colour-coded feedback after each submission.</li>
 * </ol>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public class GameController implements TypingEventHandler, GameEventListener {

    // ── FXML injected fields ──────────────────────────────────────────────────

    /** Displays the current level number in the top badge. */
    @FXML private Text labelNivel;

    /** Displays the remaining seconds on the card timer. */
    @FXML private Text labelTiempo;

    /** Progress bar that visually represents remaining time. */
    @FXML private ProgressBar progressTiempo;

    /** Shows the word or phrase the player must type. */
    @FXML private Text labelPalabra;

    /** Text field where the player types their answer. */
    @FXML private TextField campoRespuesta;

    /** Icon shown alongside the feedback message (✅ / ❌). */
    @FXML private Text iconFeedback;

    /** Text message shown after submission (correct / wrong / timed out). */
    @FXML private Text labelFeedback;

    /** Submit / validate button. */
    @FXML private Button btnValidar;

    /** Footer label showing total correct answers. */
    @FXML private Text labelAciertos;

    /** Footer label showing average time per level. */
    @FXML private Text labelPromedio;

    /** Footer restart button — returns to main menu. */
    @FXML private Button btnReiniciar;

    // ── State ─────────────────────────────────────────────────────────────────

    /** Core game session — accessed through the {@link IGame} interface. */
    private IGame gameState;

    /** JavaFX Timeline driving the per-second countdown. */
    private Timeline timeline;

    /** Accumulated time used across completed levels, for average calculation. */
    private int totalTimeUsed;

    // ── Constants ─────────────────────────────────────────────────────────────

    /** Seconds threshold below which the timer turns orange (warning). */
    private static final int TIMER_WARNING_THRESHOLD = 5;

    /** Gold colour used for normal timer display. */
    private static final String COLOR_TIMER_NORMAL  = "#C9A84C";

    /** Orange colour used when time is running low. */
    private static final String COLOR_TIMER_WARNING = "#FF7043";

    /** Green colour for correct-answer feedback. */
    private static final String COLOR_SUCCESS = "#70C870";

    /** Red colour for wrong-answer / timeout feedback. */
    private static final String COLOR_ERROR   = "#FC5C65";

    // ── Lifecycle ─────────────────────────────────────────────────────────────

    /**
     * Called automatically by {@code FXMLLoader} after all {@code @FXML} fields
     * are injected. Sets up the game state, registers event adapters, and starts
     * the first level.
     */
    @FXML
    private void initialize() {
        gameState     = new GameAdapter(new WordAdapter());
        totalTimeUsed = 0;

        // HU-2 / Heuristic 2: disable submit when field is empty (error prevention)
        btnValidar.disableProperty().bind(campoRespuesta.textProperty().isEmpty());

        // Register keyboard (Enter) and mouse (click) adapters
        KeyboardInputAdapter keyboardAdapter = new KeyboardInputAdapter(this, campoRespuesta);
        MouseSubmitAdapter   mouseAdapter    = new MouseSubmitAdapter(this, campoRespuesta);
        campoRespuesta.setOnKeyPressed(keyboardAdapter);
        btnValidar.setOnAction(mouseAdapter);

        // Restart button goes back to the main menu
        btnReiniciar.setOnAction(e -> navigateToMenu());

        startLevel();
    }

    // ── Level management ──────────────────────────────────────────────────────

    /**
     * Starts or restarts the current level.
     * Updates all UI components and relaunches the countdown timer.
     */
    private void startLevel() {
        // Level badge
        labelNivel.setText(String.format("%02d", gameState.getCurrentLevel()));

        // Word display
        labelPalabra.setText(gameState.getCurrentWord());

        // Clear input and feedback
        campoRespuesta.clear();
        campoRespuesta.setDisable(false);
        campoRespuesta.requestFocus();
        clearFeedback();

        // Heuristic 1: timer always visible and up-to-date
        updateTimerDisplay();
        startTimer();
    }

    /**
     * Starts (or restarts) the countdown {@link Timeline}.
     * Any previously running timer is stopped first to prevent overlapping ticks.
     */
    private void startTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new GameTimerTask()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Stops the countdown timer if it is currently running.
     */
    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * Updates {@code labelTiempo}, {@code progressTiempo} and the timer colour
     * based on the current remaining time.
     */
    private void updateTimerDisplay() {
        int time        = gameState.getTimeRemaining();
        int maxTime     = gameState.calculateTimeForLevel(gameState.getCurrentLevel());
        double progress = (maxTime > 0) ? (double) time / maxTime : 0.0;

        labelTiempo.setText(String.valueOf(time));
        progressTiempo.setProgress(progress);

        // Warning colour when ≤ 5 seconds
        String colour = (time <= TIMER_WARNING_THRESHOLD) ? COLOR_TIMER_WARNING : COLOR_TIMER_NORMAL;
        labelTiempo.setStyle(labelTiempo.getStyle()
                .replaceAll("-fx-fill:\\s*#[0-9A-Fa-f]{6};?", "")
                + " -fx-fill: " + colour + ";");
    }

    /**
     * Updates the footer stats (aciertos and tiempo promedio).
     */
    private void updateFooterStats() {
        int completed = gameState.getLastCompletedLevel();
        labelAciertos.setText(String.valueOf(completed));

        if (completed > 0) {
            double avg = (double) totalTimeUsed / completed;
            labelPromedio.setText(String.format("%.1f s", avg));
        }
    }

    // ── TypingEventHandler ────────────────────────────────────────────────────

    /**
     * {@inheritDoc}
     * Compares player input with the current word (exact, case-sensitive).
     * <p>
     * If correct → level up.<br>
     * If incorrect → clears the field and shows error feedback while the timer
     * keeps running so the player can retry. Game Over only happens when time
     * runs out (see {@link #onTimerExpired}).
     * </p>
     *
     * @param input the text currently entered by the player
     */
    @Override
    public void onSubmitAnswer(String input) {
        if (gameState.isGameOver()) return;

        if (gameState.getCurrentWord().equals(input)) {
            stopTimer();
            campoRespuesta.setDisable(true);

            int timeUsed = gameState.calculateTimeForLevel(gameState.getCurrentLevel())
                           - gameState.getTimeRemaining();
            totalTimeUsed += timeUsed;

            gameState.nextLevel();
            onLevelUp(gameState.getCurrentLevel());
        } else {
            // Wrong answer: clear the field and let the timer keep running
            campoRespuesta.clear();
            campoRespuesta.requestFocus();
            showFeedback(false, "❌  Incorrecto, ¡sigue intentando!");

            // Auto-hide the error hint after 1 second
            Timeline resetFeedback = new Timeline(
                    new KeyFrame(Duration.seconds(1), e -> clearFeedback()));
            resetFeedback.play();
        }
    }

    /**
     * {@inheritDoc}
     * Validates whatever the player typed when the timer hits zero.
     */
    @Override
    public void onTimerExpired() {
        Platform.runLater(() -> {
            if (gameState.isGameOver()) return;
            campoRespuesta.setDisable(true);
            String input = campoRespuesta.getText();

            if (gameState.getCurrentWord().equals(input)) {
                int timeUsed = gameState.calculateTimeForLevel(gameState.getCurrentLevel());
                totalTimeUsed += timeUsed;
                gameState.nextLevel();
                onLevelUp(gameState.getCurrentLevel());
            } else {
                showFeedback(false, "⏰  ¡Tiempo agotado!  La palabra era: \"" + gameState.getCurrentWord() + "\"");
                ScoreRecord score = new ScoreRecord(gameState.getLastCompletedLevel(), 0);
                gameState.setGameOver();                Timeline delay = new Timeline(new KeyFrame(Duration.seconds(1.5),
                        e -> navigateToGameOver(score)));
                delay.play();
            }
        });
    }

    // ── GameEventListener ─────────────────────────────────────────────────────

    /**
     * {@inheritDoc}
     * Shows a success message and schedules the next level after a short pause.
     *
     * @param level the new level number
     */
    @Override
    public void onLevelUp(int level) {
        showFeedback(true, "¡Correcto! ✦ ¡Nivel superado!");
        updateFooterStats();
        Timeline delay = new Timeline(new KeyFrame(Duration.seconds(0.8), e -> startLevel()));
        delay.play();
    }

    /**
     * {@inheritDoc}
     * Shows an error message briefly, then navigates to the game-over screen.
     *
     * @param score the final {@link ScoreRecord}
     */
    @Override
    public void onGameOver(ScoreRecord score) {
        showFeedback(false, "Incorrecto. La palabra era: \"" + gameState.getCurrentWord() + "\"");
        updateFooterStats();
        Timeline delay = new Timeline(new KeyFrame(Duration.seconds(1.5),
                e -> navigateToGameOver(score)));
        delay.play();
    }

    // ── UI helpers ────────────────────────────────────────────────────────────

    /**
     * Displays feedback text and icon with the appropriate colour.
     *
     * @param success {@code true} for correct answer (green), {@code false} for error (red)
     * @param message the message to display
     */
    private void showFeedback(boolean success, String message) {
        String colour = success ? COLOR_SUCCESS : COLOR_ERROR;
        String icon   = success ? "✅" : "❌";

        labelFeedback.setText(message);
        labelFeedback.setStyle(labelFeedback.getStyle()
                .replaceAll("-fx-fill:\\s*#[0-9A-Fa-f]{6};?", "")
                + " -fx-fill: " + colour + ";");

        iconFeedback.setText(icon);
        iconFeedback.setVisible(true);
    }

    /**
     * Clears the feedback area (icon hidden, text empty).
     */
    private void clearFeedback() {
        labelFeedback.setText("");
        iconFeedback.setVisible(false);
    }

    /**
     * Navigates to the game-over screen with the final score.
     *
     * @param score the player's final {@link ScoreRecord}
     */
    private void navigateToGameOver(ScoreRecord score) {
        Stage stage = (Stage) labelNivel.getScene().getWindow();
        try {
            GameOverStage.show(stage, score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Navigates back to the main menu (triggered by the Reiniciar footer button).
     */
    private void navigateToMenu() {
        stopTimer();
        Stage stage = (Stage) btnReiniciar.getScene().getWindow();
        try {
            MenuStage.show(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================================================================
    // Inner class: GameTimerTask
    // =========================================================================

    /**
     * Inner class acting as the per-second tick handler for the game timer.
     * <p>
     * Registered as the {@link KeyFrame} action on the game {@link Timeline}.
     * Decrements {@link IGame#getTimeRemaining()} each tick and delegates
     * to {@link TypingEventHandler#onTimerExpired()} when it reaches zero.
     * </p>
     *
     * <p>Using an inner class gives it direct access to the enclosing controller's
     * state while keeping the timer logic fully encapsulated.</p>
     */
    private class GameTimerTask implements javafx.event.EventHandler<ActionEvent> {

        /**
         * Called once per second by the enclosing {@link Timeline}.
         *
         * @param event the {@link ActionEvent} fired by the key frame
         */
        @Override
        public void handle(ActionEvent event) {
            int remaining = gameState.getTimeRemaining() - 1;
            gameState.setTimeRemaining(remaining);
            updateTimerDisplay();

            if (remaining <= 0) {
                timeline.stop();
                onTimerExpired();
            }
        }
    }
}
