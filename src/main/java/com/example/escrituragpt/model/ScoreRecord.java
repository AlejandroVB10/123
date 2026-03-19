package com.example.escrituragpt.model;

/**
 * Immutable record of the player's performance at the end of a game session.
 * <p>
 * Stores statistics such as the number of levels successfully completed
 * and the remaining time on the final level (if any).
 * </p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public class ScoreRecord {

    private final int levelsCompleted;
    private final int timeRemainingOnLastLevel;

    /**
     * Constructs a {@code ScoreRecord} with the given performance data.
     *
     * @param levelsCompleted          the number of levels the player successfully completed
     * @param timeRemainingOnLastLevel the time in seconds remaining when the game ended (0 if timed out)
     */
    public ScoreRecord(int levelsCompleted, int timeRemainingOnLastLevel) {
        this.levelsCompleted = levelsCompleted;
        this.timeRemainingOnLastLevel = timeRemainingOnLastLevel;
    }

    /**
     * Returns the number of levels the player successfully completed.
     *
     * @return levels completed count
     */
    public int getLevelsCompleted() {
        return levelsCompleted;
    }

    /**
     * Returns the time remaining in seconds on the last level when the game ended.
     *
     * @return time remaining in seconds (0 if the timer expired)
     */
    public int getTimeRemainingOnLastLevel() {
        return timeRemainingOnLastLevel;
    }

    /**
     * Returns a human-readable summary of this score record.
     *
     * @return formatted string with score statistics
     */
    @Override
    public String toString() {
        return "ScoreRecord{levelsCompleted=" + levelsCompleted
                + ", timeRemainingOnLastLevel=" + timeRemainingOnLastLevel + "}";
    }
}
