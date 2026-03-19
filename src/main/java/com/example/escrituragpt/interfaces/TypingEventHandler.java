package com.example.escrituragpt.interfaces;

/**
 * Contract for handling typing-related game events.
 * <p>
 * Extends {@link IClickTyping} to inherit the mouse-submission contract and
 * adds a timer-expiration callback. Any class that processes player input
 * during a game level must implement this interface.
 * </p>
 *
 * <p>This interface supports the <em>Open/Closed Principle</em>:
 * the event adapters depend on this abstraction rather than on concrete
 * controllers, making the system extensible without modification.</p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 * @see IClickTyping
 * @see KeyboardInputAdapter
 * @see MouseSubmitAdapter
 */
public interface TypingEventHandler extends IClickTyping {

    /**
     * Called when the level timer reaches zero before the player submits.
     */
    void onTimerExpired();
}
