package com.example.escrituragpt.service;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Centralized audio management service for background music in Escritura Rápida.
 * <p>
 * This class provides a singleton pattern to manage background music across
 * all scenes. It supports:
 * <ul>
 *   <li>Loading and playing background music for different scenes</li>
 *   <li>Stopping and resuming playback</li>
 *   <li>Adjusting volume globally</li>
 *   <li>Cycling through scenes without losing audio state</li>
 * </ul>
 * </p>
 * <p>
 * <strong>Usage:</strong> Place your music files in the {@code src/main/resources/music/} directory
 * and then call {@code AudioManager.getInstance().playMusic("scene-name", musicFileName)}.
 * </p>
 * <p>
 * Supported formats: MP3, WAV, AIFF, AAC
 * </p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public class AudioManager {

    /** Singleton instance of AudioManager. */
    private static AudioManager instance;

    /** Current MediaPlayer instance. */
    private MediaPlayer currentPlayer;

    /** Cache of loaded Media objects to avoid reloading the same file multiple times. */
    private final Map<String, Media> mediaCache;

    /** Base directory for music files relative to classpath. */
    private static final String MUSIC_DIR = "music/";

    /** Default volume level (0.0 to 1.0). */
    private double currentVolume = 0.5;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private AudioManager() {
        this.mediaCache = new HashMap<>();
    }

    /**
     * Returns the singleton instance of AudioManager.
     * <p>
     * Lazily creates the instance if it does not yet exist.
     * Thread-safe for typical GUI application use.
     * </p>
     *
     * @return the singleton {@link AudioManager} instance
     */
    public static synchronized AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    /**
     * Plays background music for a specific scene.
     * <p>
     * If music is already playing, it will be stopped and replaced with the new track.
     * The music will loop indefinitely (ideal for background music).
     * </p>
     *
     * @param sceneName descriptive name of the scene (e.g., "menu", "game", "gameOver")
     * @param musicFileName name of the music file (e.g., "menu-theme.mp3")
     * @throws IllegalArgumentException if the music file cannot be found
     */
    public void playMusic(String sceneName, String musicFileName) {
        try {
            // Stop current music if playing
            stopMusic();

            // Check if media is already cached
            if (!mediaCache.containsKey(musicFileName)) {
                String resourcePath = AudioManager.class.getResource("/" + MUSIC_DIR + musicFileName) != null
                        ? "/" + MUSIC_DIR + musicFileName
                        : MUSIC_DIR + musicFileName;

                // Try to load from classpath first
                var resource = AudioManager.class.getResource(resourcePath);
                if (resource == null) {
                    // Try to load from file system
                    File musicFile = new File("src/main/resources/" + MUSIC_DIR + musicFileName);
                    if (!musicFile.exists()) {
                        musicFile = new File(MUSIC_DIR + musicFileName);
                    }
                    if (!musicFile.exists()) {
                        throw new IllegalArgumentException(
                                "Music file not found: " + musicFileName +
                                        "\nExpected location: src/main/resources/" + MUSIC_DIR + musicFileName
                        );
                    }
                    resource = musicFile.toURI().toURL();
                }

                Media media = new Media(resource.toExternalForm());
                mediaCache.put(musicFileName, media);
            }

            // Create and configure the media player
            Media media = mediaCache.get(musicFileName);
            currentPlayer = new MediaPlayer(media);
            currentPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            currentPlayer.setVolume(currentVolume);
            currentPlayer.play();

            System.out.println("[AudioManager] Playing music for scene '" + sceneName + "': " + musicFileName);
        } catch (Exception e) {
            System.err.println("[AudioManager] Error playing music: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Stops the currently playing background music.
     * <p>
     * Does nothing if no music is currently playing.
     * </p>
     */
    public void stopMusic() {
        if (currentPlayer != null && currentPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            currentPlayer.stop();
            System.out.println("[AudioManager] Music stopped");
        }
    }

    /**
     * Pauses the currently playing background music.
     * <p>
     * The music can be resumed by calling {@link #resumeMusic()}.
     * </p>
     */
    public void pauseMusic() {
        if (currentPlayer != null && currentPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            currentPlayer.pause();
            System.out.println("[AudioManager] Music paused");
        }
    }

    /**
     * Resumes the currently paused background music.
     */
    public void resumeMusic() {
        if (currentPlayer != null && currentPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            currentPlayer.play();
            System.out.println("[AudioManager] Music resumed");
        }
    }

    /**
     * Sets the volume level for all background music.
     * <p>
     * The volume is clamped between 0.0 (silent) and 1.0 (maximum).
     * </p>
     *
     * @param volume the volume level from 0.0 to 1.0
     */
    public void setVolume(double volume) {
        this.currentVolume = Math.max(0.0, Math.min(1.0, volume));
        if (currentPlayer != null) {
            currentPlayer.setVolume(this.currentVolume);
        }
        System.out.println("[AudioManager] Volume set to: " + this.currentVolume);
    }

    /**
     * Returns the current volume level.
     *
     * @return the volume level from 0.0 to 1.0
     */
    public double getVolume() {
        return currentVolume;
    }

    /**
     * Increases the volume by 10% (up to a maximum of 1.0).
     */
    public void increaseVolume() {
        setVolume(currentVolume + 0.1);
    }

    /**
     * Decreases the volume by 10% (down to a minimum of 0.0).
     */
    public void decreaseVolume() {
        setVolume(currentVolume - 0.1);
    }

    /**
     * Checks if music is currently playing.
     *
     * @return {@code true} if music is playing; {@code false} otherwise
     */
    public boolean isPlaying() {
        return currentPlayer != null && currentPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

    /**
     * Clears the media cache to free up memory.
     * <p>
     * Useful if you need to reload music files due to external changes.
     * </p>
     */
    public void clearCache() {
        mediaCache.clear();
        System.out.println("[AudioManager] Media cache cleared");
    }
}
