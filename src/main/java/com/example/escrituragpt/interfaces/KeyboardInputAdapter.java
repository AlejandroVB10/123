package com.example.escrituragpt.interfaces;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Adapter class that bridges JavaFX {@link KeyEvent} handling with the
 * {@link TypingEventHandler} interface.
 * <p>
 * When registered on a {@link TextField}, this adapter listens for the
 * {@code ENTER} key press and delegates to {@link TypingEventHandler#onSubmitAnswer(String)}
 * with the current text of the field. All other key events are ignored.
 * </p>
 *
 * <p>Using an adapter class here avoids the need to make the game controller
 * implement {@code EventHandler<KeyEvent>} directly, preserving separation of concerns.</p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 * @see TypingEventHandler
 * @see MouseSubmitAdapter
 */
public class KeyboardInputAdapter implements EventHandler<KeyEvent> {

    private final TypingEventHandler handler;
    private final TextField inputField;

    /**
     * Constructs a {@code KeyboardInputAdapter} that will delegate
     * Enter key presses to the given handler.
     *
     * @param handler    the {@link TypingEventHandler} to notify on Enter press; must not be {@code null}
     * @param inputField the {@link TextField} whose current text will be submitted; must not be {@code null}
     */
    public KeyboardInputAdapter(TypingEventHandler handler, TextField inputField) {
        this.handler = handler;
        this.inputField = inputField;
    }

    /**
     * Handles a key event on the input field.
     * <p>
     * If the pressed key is {@link KeyCode#ENTER}, the current text of the
     * associated {@link TextField} is passed to {@link TypingEventHandler#onSubmitAnswer(String)}.
     * </p>
     *
     * @param event the {@link KeyEvent} fired by JavaFX
     */
    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handler.onSubmitAnswer(inputField.getText());
        }
    }
}
