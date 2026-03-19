package com.example.escrituragpt.interfaces;

import com.example.escrituragpt.model.ScoreRecord;

/**
 * Contract for listening to high-level game progression events.
 * <p>
 * Classes that react to level advancement or game-over transitions
 * must implement this interface. It enables loose coupling between
 * the game controller and any observers of the game lifecycle.
 * </p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public interface GameEventListener {

    /**
     * Called when the player successfully completes a level.
     *
     * @param level the new level the player has advanced to
     */
    void onLevelUp(int level);

    /**
     * Called when the game session ends, either by a wrong answer or a timer expiration.
     *
     * @param score the {@link ScoreRecord} containing the player's final statistics;
     *              must not be {@code null}
     */
    void onGameOver(ScoreRecord score);
}
