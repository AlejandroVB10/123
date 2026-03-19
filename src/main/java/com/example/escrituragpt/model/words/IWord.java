package com.example.escrituragpt.model.words;

import java.util.List;

/**
 * Contract for a word/phrase provider used in the Escritura Rápida game.
 * <p>
 * Separating word retrieval behind this interface allows the game engine
 * ({@code IGame} / {@code GameAdapter}) to be independent of <em>how</em>
 * words are sourced — whether from a hard-coded list, a file, a database,
 * or a remote API.  New word strategies can be added without modifying any
 * existing class (<em>Open/Closed Principle</em>).
 * </p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 * @see WordAdapter
 */
public interface IWord {

    /**
     * Returns a single random word or phrase to be displayed to the player.
     *
     * @return a non-null, non-empty word or phrase string
     */
    String getRandomWord();

    /**
     * Returns the complete list of available words and phrases.
     * <p>
     * Implementations should return an unmodifiable view to prevent
     * accidental mutation of the word bank.
     * </p>
     *
     * @return an unmodifiable {@link List} of all available words/phrases
     */
    List<String> getAllWords();
}
