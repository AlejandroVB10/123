# 📚 EJEMPLOS DE USO - AudioManager

## Ejemplo 1: Uso Básico (Lo que ya está implementado)

```java
// En MenuStage.java
public static void show(Stage stage) throws IOException {
    // ... código FXML ...
    stage.show();
    
    // Reproducir música del menú
    AudioManager.getInstance().playMusic("menu", "menu-theme.mp3");
}
```

## Ejemplo 2: Cambiar Volumen Dinámicamente

```java
// En MenuController.java
@FXML
private void handleVolumeUp() {
    AudioManager.getInstance().increaseVolume();
    System.out.println("Volumen: " + AudioManager.getInstance().getVolume());
}

@FXML
private void handleVolumeDown() {
    AudioManager.getInstance().decreaseVolume();
    System.out.println("Volumen: " + AudioManager.getInstance().getVolume());
}
```

## Ejemplo 3: Slider de Volumen en FXML

**menu-view.fxml:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<BorderPane xmlns="http://javafx.com/javafx/17" xmlns:fx="http://javafx.com/fxml/1"
            fx:controller="com.example.escrituragpt.controller.MenuController">
    
    <center>
        <VBox spacing="20" alignment="CENTER">
            <Label text="Escritura Rápida" style="-fx-font-size: 32;" />
            
            <!-- Control de volumen -->
            <HBox spacing="10" alignment="CENTER">
                <Label text="Volumen:" />
                <Slider fx:id="volumeSlider" min="0" max="100" value="50" 
                        prefWidth="200" />
                <Label fx:id="volumeLabel" text="50%" />
            </HBox>
            
            <Button text="Jugar" onAction="#handlePlay" />
            <Button text="Ayuda" onAction="#handleHelp" />
            <Button text="Salir" onAction="#handleExit" />
        </VBox>
    </center>
</BorderPane>
```

**MenuController.java:**
```java
package com.example.escrituragpt.controller;

import com.example.escrituragpt.service.AudioManager;
import com.example.escrituragpt.view.GameStage;
import com.example.escrituragpt.view.HelpStage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class MenuController {
    
    @FXML
    private Slider volumeSlider;
    
    @FXML
    private Label volumeLabel;
    
    @FXML
    private void initialize() {
        // Configurar slider
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(50);
        
        // Listener para cambios de volumen
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Convertir de rango 0-100 a 0.0-1.0
            double volumeLevel = newVal.doubleValue() / 100.0;
            AudioManager.getInstance().setVolume(volumeLevel);
            
            // Actualizar etiqueta
            volumeLabel.setText((int)newVal.doubleValue() + "%");
        });
    }
    
    @FXML
    private void handlePlay() {
        try {
            Stage stage = (Stage) volumeSlider.getScene().getWindow();
            GameStage.show(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleHelp() {
        try {
            Stage stage = (Stage) volumeSlider.getScene().getWindow();
            HelpStage.show(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleExit() {
        Stage stage = (Stage) volumeSlider.getScene().getWindow();
        stage.close();
    }
}
```

## Ejemplo 4: Pausar/Reanudar Música Mediante Botones

```java
// En GameController.java
@FXML
private Button pauseButton;

@FXML
private void handlePauseMusic() {
    if (AudioManager.getInstance().isPlaying()) {
        AudioManager.getInstance().pauseMusic();
        pauseButton.setText("Reanudar Música");
    } else {
        AudioManager.getInstance().resumeMusic();
        pauseButton.setText("Pausar Música");
    }
}
```

## Ejemplo 5: Verificar si la Música está Reproduciéndose

```java
// Comprobar estado de la música
if (AudioManager.getInstance().isPlaying()) {
    System.out.println("La música se está reproduciendo");
    System.out.println("Volumen actual: " + AudioManager.getInstance().getVolume());
} else {
    System.out.println("La música no se está reproduciendo");
}
```

## Ejemplo 6: Usar Diferentes Archivos para la Misma Escena

```java
// En GameController.java - por ejemplo, música diferente según dificultad

public void startGame(String difficulty) {
    String musicFile;
    
    switch(difficulty) {
        case "FÁCIL":
            musicFile = "game-easy.mp3";
            break;
        case "NORMAL":
            musicFile = "game-normal.mp3";
            break;
        case "DIFÍCIL":
            musicFile = "game-hard.mp3";
            break;
        default:
            musicFile = "game-theme.mp3";
    }
    
    AudioManager.getInstance().playMusic("game", musicFile);
}
```

## Ejemplo 7: Sistema de Notificación Sonora (Bonus)

```java
// Agregar sonidos para eventos específicos
public class GameController {
    
    private void playCorrectWordSound() {
        // Reproducir sonido de palabra correcta (sin bucle)
        AudioManager.getInstance().playMusic("notification", "correct.mp3");
    }
    
    private void playWrongWordSound() {
        // Reproducir sonido de palabra incorrecta
        AudioManager.getInstance().playMusic("notification", "wrong.mp3");
    }
    
    private void playGameOverSound() {
        // Reproducir sonido de fin de juego
        AudioManager.getInstance().playMusic("notification", "gameover.wav");
    }
}
```

**Nota:** Para esto necesitarías modificar `AudioManager` para soportar reproducción sin bucle.

## Ejemplo 8: Limpiar Caché si es Necesario

```java
// Útil si cambias archivos durante la ejecución
public void reloadAudio() {
    AudioManager.getInstance().clearCache();
    // Ahora los archivos se cargarán nuevamente
    AudioManager.getInstance().playMusic("menu", "menu-theme.mp3");
}
```

## Ejemplo 9: Estructura Completa de GameController

```java
package com.example.escrituragpt.controller;

import com.example.escrituragpt.service.AudioManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameController {
    
    @FXML
    private Button pauseMusicButton;
    
    @FXML
    private void initialize() {
        // Inicialización del controlador
        // La música ya se está reproduciendo desde GameStage.show()
    }
    
    @FXML
    private void handlePauseMusic(ActionEvent event) {
        AudioManager manager = AudioManager.getInstance();
        
        if (manager.isPlaying()) {
            manager.pauseMusic();
            pauseMusicButton.setText("Reanudar");
        } else {
            manager.resumeMusic();
            pauseMusicButton.setText("Pausar");
        }
    }
    
    @FXML
    private void handleVolumeUp(ActionEvent event) {
        AudioManager.getInstance().increaseVolume();
        System.out.println("Volumen subido a: " 
            + AudioManager.getInstance().getVolume());
    }
    
    @FXML
    private void handleVolumeDown(ActionEvent event) {
        AudioManager.getInstance().decreaseVolume();
        System.out.println("Volumen bajado a: " 
            + AudioManager.getInstance().getVolume());
    }
}
```

## Ejemplo 10: Test Unitario (Bonus)

```java
package com.example.escrituragpt.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AudioManagerTest {
    
    @Test
    public void testSingletonPattern() {
        AudioManager manager1 = AudioManager.getInstance();
        AudioManager manager2 = AudioManager.getInstance();
        
        assertSame(manager1, manager2, "Debe retornar la misma instancia");
    }
    
    @Test
    public void testVolumeControl() {
        AudioManager manager = AudioManager.getInstance();
        
        manager.setVolume(0.5);
        assertEquals(0.5, manager.getVolume(), 0.0, "El volumen debe ser 0.5");
        
        manager.setVolume(1.5);  // Será limitado a 1.0
        assertEquals(1.0, manager.getVolume(), 0.0, "El volumen máximo es 1.0");
        
        manager.setVolume(-0.5);  // Será limitado a 0.0
        assertEquals(0.0, manager.getVolume(), 0.0, "El volumen mínimo es 0.0");
    }
    
    @Test
    public void testVolumeIncreaseDecrease() {
        AudioManager manager = AudioManager.getInstance();
        
        manager.setVolume(0.5);
        manager.increaseVolume();
        assertEquals(0.6, manager.getVolume(), 0.01);
        
        manager.decreaseVolume();
        assertEquals(0.5, manager.getVolume(), 0.01);
    }
}
```

---

## 📝 Flujo Completo: Inicio a Fin

```
Usuario inicia la aplicación
    ↓
Main.java llama a MenuStage.show()
    ↓
MenuStage.show() reproduce "menu-theme.mp3"
    ↓
Usuario ve el menú con volumen slider
    ↓
Usuario ajusta el volumen
    ↓
MenuController.initialize() escucha cambios del slider
    ↓
AudioManager.getInstance().setVolume() actualiza volumen
    ↓
Usuario hace clic en "Jugar"
    ↓
MenuController.handlePlay() → GameStage.show()
    ↓
GameStage.show() detiene "menu-theme.mp3" y reproduce "game-theme.mp3"
    ↓
Usuario juega
    ↓
GameController detecta fin del juego
    ↓
GameOverStage.show() detiene "game-theme.mp3" y reproduce "gameover-theme.mp3"
    ↓
Usuario ve estadísticas
    ↓
Usuario hace clic en "Volver al Menú"
    ↓
GameOverController → MenuStage.show()
    ↓
Ciclo se repite
```

---

## 🎯 Checklist para Implementar Ejemplos

- [ ] Descargar 4 archivos MP3 de https://incompetech.com/
- [ ] Colocar en `src/main/resources/music/`
- [ ] Compilar con `mvn clean compile`
- [ ] Ejecutar la aplicación
- [ ] Escuchar música en menú
- [ ] Escuchar cambio de música al jugar
- [ ] Ajustar volumen si agregaste slider
- [ ] Pausar/reanudar si agregaste botones

---

¡Éxito! 🎵🎮
