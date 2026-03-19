package com.example.escrituragpt.model.game;

import com.example.escrituragpt.model.ScoreRecord;

/**
 * Contract that defines the core behaviour of a game session in Escritura Rápida.
 * <p>
 * Any class that manages game state (levels, time, progression) must implement
 * this interface. Decoupling the controller from a concrete implementation
 * keeps the system open to extension (e.g. a timed-challenge mode or a
 * multiplayer variant) without modifying existing code
 * (<em>Open/Closed Principle</em>).
 * </p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 * @see GameAdapter
 */
public interface IGame {

    /**
     * Resets the game session to its initial state (level 1, full time, new word).
     */
    void reset();

    /**
     * Advances the game to the next level, updating time and picking a new word.
     */
    void nextLevel();

    /**
     * Marks the current session as finished.
     */
    void setGameOver();

    /**
     * Returns the current level number (1-based).
     *
     * @return current level
     */
    int getCurrentLevel();

    /**
     * Returns the remaining time in seconds for the current level.
     *
     * @return seconds remaining
     */
    int getTimeRemaining();

    /**
     * Sets the remaining time for the current level.
     *
     * @param seconds new time value in seconds
     */
    void setTimeRemaining(int seconds);

    /**
     * Returns the word or phrase the player must type in the current level.
     *
     * @return current target word/phrase
     */
    String getCurrentWord();

    /**
     * Returns whether the game session has ended.
     *
     * @return {@code true} if game over
     */
    boolean isGameOver();

    /**
     * Returns the last successfully completed level number.
     *
     * @return last completed level (0 if none)
     */
    int getLastCompletedLevel();

    /**
     * Calculates the time available for a given level number.
     * <p>
     * Every {@code LEVELS_PER_TIME_REDUCTION} levels the time decreases
     * by {@code TIME_REDUCTION_STEP} seconds, down to a minimum.
     * </p>
     *
     * @param level the level number to evaluate
     * @return time in seconds for that level
     */
    int calculateTimeForLevel(int level);

    /**
     * Builds and returns a {@link ScoreRecord} representing the current
     * end-of-game statistics.
     *
     * @return a snapshot of the player's final score
     */
    ScoreRecord buildScoreRecord();
}
