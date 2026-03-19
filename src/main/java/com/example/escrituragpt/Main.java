package com.example.escrituragpt;

import com.example.escrituragpt.view.MenuStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main entry point of the Escritura Rápida application.
 * <p>
 * Extends {@link Application} to bootstrap the JavaFX runtime and delegates
 * the initial scene loading to {@link MenuStage}, which displays the main menu.
 * This class replaces the former {@code HelloApplication} / {@code Launcher}
 * pair, unifying application startup into a single, clearly named class.
 * </p>
 *
 * @author EscrituraRápida Team
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Called by the JavaFX runtime to initialize the primary stage.
     * Loads and displays the main menu screen.
     *
     * @param stage the primary {@link Stage} provided by JavaFX
     * @throws IOException if the menu FXML resource cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        MenuStage.show(stage);
        //hola
    }

    /**
     * Application entry point.
     * <p>
     * Calls {@link Application#launch(Class, String...)} to start the JavaFX
     * runtime. Keeping {@code main} here (instead of a separate launcher class)
     * works correctly because this module explicitly requires {@code javafx.controls}
     * and {@code javafx.fxml}, so JavaFX is always on the module path.
     * </p>
     *
     * @param args command-line arguments forwarded to the JavaFX runtime
     */
    public static void main(String[] args) {
        launch(args);
    }
}
