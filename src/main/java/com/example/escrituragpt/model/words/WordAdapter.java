package com.example.escrituragpt.model.words;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Concrete adapter that implements {@link IWord} using a built-in
 * Spanish word and phrase bank.
 * <p>
 * This class adapts the static word list into the {@link IWord} contract,
 * following the <em>Adapter</em> pattern: the controller and game engine
 * depend only on {@link IWord}, so the word source can be replaced
 * (e.g. words loaded from a file or API) without changing any other class.
 * </p>
 *
 * <p>The bank contains words at three difficulty levels mixed randomly:</p>
 * <ul>
 *   <li>Simple words (single token)</li>
 *   <li>Medium compound/technical words</li>
 *   <li>Short phrases (with spaces and punctuation)</li>
 * </ul>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 * @see IWord
 */
public class WordAdapter implements IWord {

    /** Complete, unmodifiable list of available words and phrases. */
    private static final List<String> WORD_LIST = Collections.unmodifiableList(Arrays.asList(
            // ── Simple words ──
            "casa", "perro", "libro", "árbol", "cielo",
            "agua", "fuego", "tierra", "viento", "luna",
            "sol", "estrella", "mar", "río", "monte",
            "ciudad", "pueblo", "camino", "puente", "jardín",
            "música", "pintura", "danza", "teatro", "cine",
            "tiempo", "espacio", "mundo", "vida", "amor",
            // ── Medium / technical words ──
            "computadora", "teclado", "pantalla", "ratón", "escritorio",
            "programar", "desarrollar", "compilar", "ejecutar", "depurar",
            "algoritmo", "variable", "función", "método", "objeto",
            "biblioteca", "interfaz", "herencia", "polimorfismo", "abstracción",
            // ── Short phrases ──
            "hola mundo", "café caliente", "noche estrellada",
            "viento suave", "lluvia fría", "día soleado",
            "libro abierto", "camino largo", "río profundo",
            "ciudad dormida", "jardín florido", "mar tranquilo",
            // ── Phrases with punctuation ──
            "¡hola, mundo!", "¿cómo estás?", "¡muy bien, gracias!",
            "el tiempo vuela.", "la vida es bella.", "sigue adelante.",
            "escribe rápido!", "no te rindas.", "puedes lograrlo!",
            "a toda velocidad.", "momento de gloria.", "hazlo bien."
    ));

    private static final Random RANDOM = new Random();

    /**
     * Constructs a new {@code WordAdapter} backed by the built-in word bank.
     */
    public WordAdapter() {
        // Default constructor — no configuration needed for the built-in bank.
    }

    /**
     * {@inheritDoc}
     * Picks a uniformly random entry from {@link #WORD_LIST}.
     */
    @Override
    public String getRandomWord() {
        return WORD_LIST.get(RANDOM.nextInt(WORD_LIST.size()));
    }

    /**
     * {@inheritDoc}
     * Returns the complete, unmodifiable built-in word list.
     */
    @Override
    public List<String> getAllWords() {
        return WORD_LIST;
    }
}
