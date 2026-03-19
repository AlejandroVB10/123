# 🎵 Sistema de Música de Fondo - Guía de Implementación

## Descripción General

Se ha implementado un sistema centralizado de gestión de música de fondo (`AudioManager`) que permite reproducir música en todas las escenas del juego de forma fácil y flexible.

## Características

✅ Música de fondo en **4 escenas** diferentes:
- **Menú principal** (Menu)
- **Pantalla de juego** (Game)
- **Pantalla de ayuda** (Help)
- **Pantalla de game over** (GameOver)

✅ **Control de volumen** global
✅ **Reproducción en bucle** (INDEFINITE)
✅ **Cache de medios** para evitar cargar archivos múltiples veces
✅ **Pausa y reanudación** de música
✅ **Manejo de errores** robusto

## Estructura de Archivos

```
src/main/resources/
└── music/
    ├── menu-theme.mp3          (Música del menú)
    ├── game-theme.mp3          (Música del juego)
    ├── help-theme.mp3          (Música de ayuda)
    └── gameover-theme.mp3      (Música de game over)
```

## Pasos de Instalación

### 1. Crear la carpeta de música
```bash
mkdir -p src/main/resources/music/
```

### 2. Descargar los archivos de música

Descarga tus archivos de música favoritos en formato **MP3**, **WAV**, **AIFF** o **AAC** y colócalos en la carpeta anterior:

- `menu-theme.mp3` - Para la pantalla del menú
- `game-theme.mp3` - Para la pantalla de juego
- `help-theme.mp3` - Para la pantalla de ayuda
- `gameover-theme.mp3` - Para la pantalla de fin del juego

### 3. Compilar el proyecto

```bash
mvn clean compile
```

## Cómo Personalizar

### Cambiar el nombre de los archivos de música

Si deseas usar otros nombres para los archivos, edita los siguientes archivos:

**MenuStage.java:**
```java
AudioManager.getInstance().playMusic("menu", "TU-ARCHIVO.mp3");
```

**GameStage.java:**
```java
AudioManager.getInstance().playMusic("game", "TU-ARCHIVO.mp3");
```

**HelpStage.java:**
```java
AudioManager.getInstance().playMusic("help", "TU-ARCHIVO.mp3");
```

**GameOverStage.java:**
```java
AudioManager.getInstance().playMusic("gameOver", "TU-ARCHIVO.mp3");
```

## API de AudioManager

### Métodos Principales

```java
// Reproducir música
AudioManager.getInstance().playMusic(String sceneName, String musicFileName);

// Detener música
AudioManager.getInstance().stopMusic();

// Pausar/Reanudar
AudioManager.getInstance().pauseMusic();
AudioManager.getInstance().resumeMusic();

// Control de volumen (0.0 a 1.0)
AudioManager.getInstance().setVolume(0.5);      // 50%
AudioManager.getInstance().increaseVolume();    // +10%
AudioManager.getInstance().decreaseVolume();    // -10%
double volume = AudioManager.getInstance().getVolume();

// Estado de reproducción
boolean isPlaying = AudioManager.getInstance().isPlaying();

// Limpiar caché
AudioManager.getInstance().clearCache();
```

## Ejemplo de Uso Avanzado

Si deseas agregar un control de volumen en el menú, puedes hacerlo así:

```java
@FXML
private Slider volumeSlider;

@FXML
private void initialize() {
    volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
        AudioManager.getInstance().setVolume(newVal.doubleValue() / 100.0);
    });
}
```

## Solución de Problemas

### La música no se escucha

**Problema:** `Music file not found: menu-theme.mp3`

**Solución:**
1. Asegúrate de que la carpeta `src/main/resources/music/` existe
2. Verifica que los archivos están en la carpeta correcta
3. Compila el proyecto nuevamente: `mvn clean compile`
4. Revisa la consola para mensajes de depuración

### La música se detiene cuando cambio de escena

**Comportamiento esperado:** Esto es intencional. Cuando navegas a una nueva escena, la música anterior se detiene y comienza la nueva. Si deseas mantener la misma música en múltiples escenas, simplemente usa el mismo nombre de archivo.

### Formatos de audio no soportados

JavaFX soporta:
- MP3 ✅
- WAV ✅
- AIFF ✅
- AAC ✅

No soporta: FLAC, OGG, WMA

## Diagrama de Flujo

```
Main.java
    ↓
MenuStage.show() → AudioManager.playMusic("menu", "menu-theme.mp3")
    ↓
MenuController → GameStage.show() → AudioManager.playMusic("game", "game-theme.mp3")
    ↓
GameController → GameOverStage.show() → AudioManager.playMusic("gameOver", "gameover-theme.mp3")
    ↓
GameOverController → MenuStage.show() → AudioManager.playMusic("menu", "menu-theme.mp3")
```

## Clases Involucradas

- **AudioManager.java** - Servicio centralizado de audio (patrón Singleton)
- **MenuStage.java** - Carga y muestra menú + música
- **GameStage.java** - Carga y muestra juego + música
- **HelpStage.java** - Carga y muestra ayuda + música
- **GameOverStage.java** - Carga y muestra game over + música

## Notas Técnicas

- **Singleton Pattern:** Solo existe una instancia de `AudioManager` en toda la aplicación
- **Lazy Initialization:** El `AudioManager` se crea cuando se necesita por primera vez
- **Media Caching:** Los archivos de audio se cargan en memoria una sola vez
- **Infinite Cycling:** La música se reproduce en bucle indefinido
- **Thread-Safe:** Compatible con el modelo de threading de JavaFX

## Fuentes Recomendadas para Descargar Música

- **Free Music Archive:** https://freemusicarchive.org/
- **Incompetech:** https://incompetech.com/
- **Pixabay Music:** https://pixabay.com/music/
- **YouTube Audio Library:** https://www.youtube.com/audiolibrary

Busca música con licencia CC0 o Creative Commons para uso personal/educativo.

---

**¡Disfruta del sistema de música! 🎶**
