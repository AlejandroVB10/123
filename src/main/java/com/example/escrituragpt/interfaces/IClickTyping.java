package com.example.escrituragpt.interfaces;

/**
 * Contract for handling mouse-driven typing submission events.
 * <p>
 * Separating click-based submission into its own interface allows
 * {@link MouseSubmitAdapter} to depend on the narrowest possible contract,
 * following the <em>Interface Segregation Principle</em>.
 * Any class that only needs to react to button clicks implements this
 * interface instead of the broader {@link TypingEventHandler}.
 * </p>
 *
 * <p>In the current architecture {@link TypingEventHandler} extends this
 * interface so that a single controller can satisfy both contracts without
 * duplication.</p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 * @see TypingEventHandler
 * @see MouseSubmitAdapter
 */
public interface IClickTyping {

    /**
     * Called when the player clicks the submit button or triggers an equivalent
     * mouse/pointer action to validate their typed answer.
     *
     * @param input the text currently in the input field at the moment of the
     *              click; must not be {@code null}
     */
    void onSubmitAnswer(String input);
}
