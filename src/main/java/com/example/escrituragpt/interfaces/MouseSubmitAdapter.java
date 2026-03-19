package com.example.escrituragpt.interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/**
 * Adapter class that bridges JavaFX button {@link ActionEvent} handling with
 * the {@link IClickTyping} interface.
 * <p>
 * Depends on the narrowest possible contract ({@link IClickTyping}) rather
 * than the broader {@link TypingEventHandler}, following the
 * <em>Interface Segregation Principle</em>: this adapter only needs to know
 * about click-based submission, not about timer expiration.
 * </p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 * @see IClickTyping
 * @see KeyboardInputAdapter
 */
public class MouseSubmitAdapter implements EventHandler<ActionEvent> {

    private final IClickTyping handler;
    private final TextField inputField;

    /**
     * Constructs a {@code MouseSubmitAdapter}.
     *
     * @param handler    the {@link IClickTyping} to notify on button click; must not be {@code null}
     * @param inputField the {@link TextField} whose text is submitted; must not be {@code null}
     */
    public MouseSubmitAdapter(IClickTyping handler, TextField inputField) {
        this.handler    = handler;
        this.inputField = inputField;
    }

    /**
     * Handles the button action event, forwarding the field text to
     * {@link IClickTyping#onSubmitAnswer(String)}.
     *
     * @param event the {@link ActionEvent} fired by the JavaFX button
     */
    @Override
    public void handle(ActionEvent event) {
        handler.onSubmitAnswer(inputField.getText());
    }
}
