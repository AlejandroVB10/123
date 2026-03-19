package com.example.escrituragpt.model.game;

import com.example.escrituragpt.model.ScoreRecord;
import com.example.escrituragpt.model.words.IWord;

/**
 * Concrete adapter that implements {@link IGame} to manage a standard
 * Escritura Rápida game session.
 * <p>
 * This class acts as an <em>Adapter</em> in the GoF sense: it adapts the raw
 * game-state data (level counter, time, word) into the {@link IGame} contract
 * consumed by the controller.  By depending on {@link IWord} rather than a
 * concrete word source, the word-selection strategy can be swapped without
 * touching this class.
 * </p>
 *
 * <p>Progression rules:</p>
 * <ul>
 *   <li>Initial time: {@value #INITIAL_TIME_SECONDS} seconds.</li>
 *   <li>Every {@value #LEVELS_PER_TIME_REDUCTION} levels time drops by
 *       {@value #TIME_REDUCTION_STEP} seconds.</li>
 *   <li>Minimum time per level: {@value #MIN_TIME_SECONDS} seconds.</li>
 * </ul>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 * @see IGame
 */
public class GameAdapter implements IGame {

    /** Initial time in seconds for the first level. */
    public static final int INITIAL_TIME_SECONDS     = 20;

    /** Number of levels required to trigger a time reduction. */
    public static final int LEVELS_PER_TIME_REDUCTION = 5;

    /** Seconds to subtract per reduction step. */
    public static final int TIME_REDUCTION_STEP       = 2;

    /** Minimum time allowed per level in seconds. */
    public static final int MIN_TIME_SECONDS          = 2;

    private final IWord wordSource;

    private int    currentLevel;
    private int    timeRemaining;
    private String currentWord;
    private boolean gameOver;
    private int    lastCompletedLevel;

    /**
     * Constructs a {@code GameAdapter} using the given word source.
     *
     * @param wordSource the {@link IWord} implementation that supplies target
     *                   words each level; must not be {@code null}
     */
    public GameAdapter(IWord wordSource) {
        this.wordSource = wordSource;
        reset();
    }

    /** {@inheritDoc} */
    @Override
    public void reset() {
        currentLevel       = 1;
        timeRemaining      = INITIAL_TIME_SECONDS;
        currentWord        = wordSource.getRandomWord();
        gameOver           = false;
        lastCompletedLevel = 0;
    }

    /** {@inheritDoc} */
    @Override
    public void nextLevel() {
        lastCompletedLevel = currentLevel;
        currentLevel++;
        currentWord   = wordSource.getRandomWord();
        timeRemaining = calculateTimeForLevel(currentLevel);
    }

    /** {@inheritDoc} */
    @Override
    public void setGameOver() {
        gameOver = true;
    }

    /** {@inheritDoc} */
    @Override
    public int calculateTimeForLevel(int level) {
        int reductions = (level - 1) / LEVELS_PER_TIME_REDUCTION;
        int time = INITIAL_TIME_SECONDS - (reductions * TIME_REDUCTION_STEP);
        return Math.max(time, MIN_TIME_SECONDS);
    }

    /** {@inheritDoc} */
    @Override
    public ScoreRecord buildScoreRecord() {
        return new ScoreRecord(lastCompletedLevel, timeRemaining);
    }

    /** {@inheritDoc} */
    @Override
    public int getCurrentLevel() { return currentLevel; }

    /** {@inheritDoc} */
    @Override
    public int getTimeRemaining() { return timeRemaining; }

    /** {@inheritDoc} */
    @Override
    public void setTimeRemaining(int seconds) { this.timeRemaining = seconds; }

    /** {@inheritDoc} */
    @Override
    public String getCurrentWord() { return currentWord; }

    /** {@inheritDoc} */
    @Override
    public boolean isGameOver() { return gameOver; }

    /** {@inheritDoc} */
    @Override
    public int getLastCompletedLevel() { return lastCompletedLevel; }
}
