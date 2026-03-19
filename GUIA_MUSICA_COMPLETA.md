# 🎵 GUÍA DEFINITIVA: Sistema de Música de Fondo para Escritura Rápida

## 📋 Descripción General

Se ha implementado un **sistema centralizado y robusto de gestión de música de fondo** que permite reproducir música en todas las escenas del juego. El sistema está basado en el patrón **Singleton** y utiliza la API `MediaPlayer` de JavaFX.

---

## ✨ Características Implementadas

| Característica | Descripción |
|---|---|
| **🎵 Música en 4 escenas** | Menú, Juego, Ayuda y Game Over |
| **🔊 Control de volumen** | Aumentar, disminuir y establecer volumen global |
| **🔁 Reproducción en bucle** | La música se repite indefinidamente |
| **💾 Cache de medios** | Los archivos se cargan una sola vez en memoria |
| **⏸️ Pausa/Reanudación** | Pausa y reanuda la música en cualquier momento |
| **🛡️ Manejo robusto de errores** | Previene crashes si falta un archivo de audio |
| **🔧 Configuración flexible** | Cambia archivos fácilmente sin modificar código |

---

## 📁 Estructura de Carpetas

Debes crear la siguiente estructura en tu proyecto:

```
Escrituragpt - copia/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/escrituragpt/
│       │       ├── service/
│       │       │   └── AudioManager.java          ✅ (CREADO)
│       │       ├── view/
│       │       │   ├── MenuStage.java             ✅ (ACTUALIZADO)
│       │       │   ├── GameStage.java             ✅ (ACTUALIZADO)
│       │       │   ├── HelpStage.java             ✅ (ACTUALIZADO)
│       │       │   └── GameOverStage.java         ✅ (ACTUALIZADO)
│       │       └── ... (otros archivos)
│       └── resources/
│           └── music/                             ⬅️ CREA ESTA CARPETA
│               ├── menu-theme.mp3                 📥 DESCARGA AQUÍ
│               ├── game-theme.mp3                 📥 DESCARGA AQUÍ
│               ├── help-theme.mp3                 📥 DESCARGA AQUÍ
│               └── gameover-theme.mp3             📥 DESCARGA AQUÍ
```

---

## 🚀 PASOS DE INSTALACIÓN (MUY SIMPLE)

### 1️⃣ Crear la carpeta de música

Abre tu terminal o explorador de archivos y crea la carpeta:

```bash
mkdir -p src/main/resources/music
```

O manualmente:
- Ve a `Escrituragpt - copia/src/main/resources/`
- Crea una carpeta nueva llamada `music`

### 2️⃣ Descargar archivos de música

Visita una de estas páginas y **descarga 4 archivos MP3**:
- **Incompetech:** https://incompetech.com/
- **Pixabay Music:** https://pixabay.com/music/
- **Free Music Archive:** https://freemusicarchive.org/
- **YouTube Audio Library:** https://www.youtube.com/audiolibrary

**Tips:**
- Busca música relajante o dinámica
- Prefiere archivos con licencia CC0 o Creative Commons
- Los archivos deben estar en **formato MP3, WAV, AIFF o AAC**

### 3️⃣ Colocar archivos en la carpeta

Renombra tus archivos descargados así y colócalos en `src/main/resources/music/`:

| Escena | Nombre de archivo | Recomendación |
|---|---|---|
| **Menú** | `menu-theme.mp3` | Música calma, welcoming |
| **Juego** | `game-theme.mp3` | Música energética, dinámica |
| **Ayuda** | `help-theme.mp3` | Música informativa, suave |
| **Game Over** | `gameover-theme.mp3` | Música dramática o conclusiva |

### 4️⃣ Compilar el proyecto

```bash
cd "Escrituragpt - copia"
./mvnw clean compile
```

O en Windows PowerShell:
```powershell
cd "C:\Users\USUARIO\IdeaProjects\Escrituragpt - copia"
.\mvnw.cmd clean compile
```

Si ves `BUILD SUCCESS` ✅ ¡Todo está listo!

---

## 🎯 Cómo Usar AudioManager

### API Completa

```java
// Singleton - obtener la instancia única
AudioManager manager = AudioManager.getInstance();

// ▶️ REPRODUCIR MÚSICA
manager.playMusic("escena", "archivo.mp3");
manager.playMusic("menu", "menu-theme.mp3");

// ⏹️ DETENER MÚSICA
manager.stopMusic();

// ⏸️ PAUSAR
manager.pauseMusic();

// ▶️ REANUDAR
manager.resumeMusic();

// 🔊 VOLUMEN (0.0 a 1.0)
manager.setVolume(0.5);           // 50%
manager.setVolume(0.8);           // 80%
manager.setVolume(1.0);           // 100% (máximo)

// 📈 AUMENTAR VOLUMEN (+10%)
manager.increaseVolume();

// 📉 DISMINUIR VOLUMEN (-10%)
manager.decreaseVolume();

// 🔍 OBTENER VOLUMEN ACTUAL
double volumen = manager.getVolume();  // Retorna 0.0 a 1.0

// ❓ VERIFICAR SI ESTÁ REPRODUCIENDO
boolean reproduciendo = manager.isPlaying();

// 🧹 LIMPIAR CACHÉ (libera memoria)
manager.clearCache();
```

---

## 🎮 Flujo Actual en el Juego

```
┌─────────────────────────────────────────────────────────────┐
│                      Main.java (Inicio)                      │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
        ┌──────────────────────────────────────┐
        │   MenuStage.show()                    │
        │   🎵 Toca: menu-theme.mp3            │
        │   (Menú Principal)                    │
        └──────┬───────────────────────────────┘
               │ (Usuario hace clic en "Jugar")
               ▼
        ┌──────────────────────────────────────┐
        │   GameStage.show()                    │
        │   🎵 Toca: game-theme.mp3            │
        │   (Pantalla de Juego)                 │
        └──────┬───────────────────────────────┘
               │ (Fin del juego)
               ▼
        ┌──────────────────────────────────────┐
        │   GameOverStage.show()                │
        │   🎵 Toca: gameover-theme.mp3        │
        │   (Pantalla de Game Over)             │
        └──────┬───────────────────────────────┘
               │ (Usuario hace clic en "Volver")
               ▼
        ┌──────────────────────────────────────┐
        │   MenuStage.show()                    │
        │   🎵 Toca: menu-theme.mp3            │
        │   (Menú Principal - Ciclo)            │
        └──────────────────────────────────────┘
```

---

## 🔧 Personalización Avanzada

### Cambiar los nombres de los archivos

Si deseas usar otros nombres para los archivos, edita:

**`MenuStage.java` (línea ~50):**
```java
// ANTES:
AudioManager.getInstance().playMusic("menu", "menu-theme.mp3");

// DESPUÉS (si tu archivo se llama "main-music.mp3"):
AudioManager.getInstance().playMusic("menu", "main-music.mp3");
```

**`GameStage.java` (línea ~50):**
```java
AudioManager.getInstance().playMusic("game", "game-theme.mp3");
// Cambiar a: AudioManager.getInstance().playMusic("game", "mi-musica-juego.mp3");
```

**`HelpStage.java` (línea ~51):**
```java
AudioManager.getInstance().playMusic("help", "help-theme.mp3");
```

**`GameOverStage.java` (línea ~58):**
```java
AudioManager.getInstance().playMusic("gameOver", "gameover-theme.mp3");
```

### Agregar Control de Volumen en el Menú

Si tienes un slider en tu FXML:

```java
@FXML
private Slider volumeSlider;  // ID en FXML: volumeSlider

@FXML
private void initialize() {
    // Rango del slider: 0 a 100
    volumeSlider.setMin(0);
    volumeSlider.setMax(100);
    volumeSlider.setValue(50);
    
    // Escuchar cambios del slider
    volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
        // Convertir rango 0-100 a 0.0-1.0
        double volumeLevel = newVal.doubleValue() / 100.0;
        AudioManager.getInstance().setVolume(volumeLevel);
    });
}
```

### Usar la misma música en múltiples escenas

Si quieres que el menú y la ayuda tengan la **misma música**:

```java
// En HelpStage.java
// CAMBIAR:
AudioManager.getInstance().playMusic("help", "help-theme.mp3");

// A:
AudioManager.getInstance().playMusic("help", "menu-theme.mp3");
```

---

## ❌ Solución de Problemas

### 📌 Problema: "Music file not found: menu-theme.mp3"

**Causa:** El archivo no existe en la carpeta correcta.

**Solución:**
1. Verifica que la carpeta existe: `src/main/resources/music/`
2. Verifica que el archivo `menu-theme.mp3` está en esa carpeta
3. Recompila: `.\mvnw.cmd clean compile`
4. Revisa los mensajes en la consola

### 📌 Problema: No se escucha música

**Causa 1:** El volumen del sistema está muy bajo
- ✅ Aumenta el volumen del sistema operativo

**Causa 2:** JavaFX no puede reproducir el formato
- ✅ Convierte a MP3: usa https://cloudconvert.com/ o VLC

**Causa 3:** Ruta incorrecta del archivo
- ✅ Verifica que la carpeta `music/` está en `src/main/resources/`

### 📌 Problema: Error de compilación "cannot find symbol: AudioManager"

**Causa:** Las importaciones no se agregaron correctamente

**Solución:**
- Verifica que todas las clases Stage tengan: `import com.example.escrituragpt.service.AudioManager;`
- Recompila: `.\mvnw.cmd clean compile`

### 📌 Problema: La música se corta al cambiar de escena

**Comportamiento esperado:** Esto es intencional. La música antigua se detiene y comienza la nueva. Si quieres mantener la música, usa el mismo nombre de archivo.

---

## 🎵 Formatos Soportados

JavaFX soporta estos formatos de audio:

| Formato | Soportado | Recomendado |
|---|---|---|
| **MP3** | ✅ Sí | ⭐ Recomendado |
| **WAV** | ✅ Sí | ⭐ Excelente calidad |
| **AIFF** | ✅ Sí | ✅ Funciona |
| **AAC** | ✅ Sí | ✅ Funciona |
| **FLAC** | ❌ No | ❌ No soportado |
| **OGG** | ❌ No | ❌ No soportado |
| **WMA** | ❌ No | ❌ No soportado |

**Recomendación:** Usa **MP3** por compatibilidad y tamaño.

---

## 🏗️ Arquitectura del Sistema

### Patrón Singleton

```
┌─────────────────────────────────────┐
│     AudioManager (Singleton)         │
├─────────────────────────────────────┤
│ - instance: AudioManager             │
│ - currentPlayer: MediaPlayer         │
│ - mediaCache: Map<String, Media>     │
├─────────────────────────────────────┤
│ + getInstance(): AudioManager        │
│ + playMusic(scene, file)             │
│ + stopMusic()                        │
│ + pauseMusic()                       │
│ + resumeMusic()                      │
│ + setVolume(double)                  │
│ + getVolume(): double                │
│ + isPlaying(): boolean               │
└─────────────────────────────────────┘
         ▲
         │ usada por
         │
    ┌────┴──────────────────────────────┐
    │                                    │
┌───────┐  ┌──────┐  ┌────┐  ┌────────┐│
│ Menu  │  │ Game │  │Help│  │GameOver││
│ Stage │  │ Stage│  │St. │  │ Stage  ││
└───────┘  └──────┘  └────┘  └────────┘│
```

### Archivos Involucrados

1. **`AudioManager.java`** (Nueva)
   - Servicio centralizado
   - Gestiona reproducción de audio
   - Implementa patrón Singleton

2. **`MenuStage.java`** (Modificado)
   - Reproducir `menu-theme.mp3`

3. **`GameStage.java`** (Modificado)
   - Reproducir `game-theme.mp3`

4. **`HelpStage.java`** (Modificado)
   - Reproducir `help-theme.mp3`

5. **`GameOverStage.java`** (Modificado)
   - Reproducir `gameover-theme.mp3`

6. **`module-info.java`** (Actualizado)
   - Agregado `requires javafx.media;`
   - Agregado `exports com.example.escrituragpt.service;`

7. **`pom.xml`** (Actualizado)
   - Agregada dependencia `javafx-media`

---

## 🎬 Ejemplo Completo: Crear Control de Volumen

Si quieres agregar un control de volumen en el menú:

**Edita `menu-view.fxml`:**
```xml
<HBox spacing="10">
    <Label text="Volumen:" />
    <Slider fx:id="volumeSlider" min="0" max="100" value="50" />
    <Label fx:id="volumeLabel" text="50%" />
</HBox>
```

**Edita `MenuController.java`:**
```java
@FXML
private Slider volumeSlider;

@FXML
private Label volumeLabel;

@FXML
private void initialize() {
    volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
        double volumeLevel = newVal.doubleValue() / 100.0;
        AudioManager.getInstance().setVolume(volumeLevel);
        volumeLabel.setText((int)newVal.doubleValue() + "%");
    });
}
```

---

## 📊 Tabla de Métodos AudioManager

| Método | Parámetros | Retorna | Descripción |
|---|---|---|---|
| `getInstance()` | - | `AudioManager` | Obtiene instancia singleton |
| `playMusic()` | `String scene, String file` | `void` | Reproduce música |
| `stopMusic()` | - | `void` | Detiene la música |
| `pauseMusic()` | - | `void` | Pausa la música |
| `resumeMusic()` | - | `void` | Reanuda la música |
| `setVolume()` | `double (0.0-1.0)` | `void` | Establece volumen |
| `getVolume()` | - | `double` | Obtiene volumen actual |
| `increaseVolume()` | - | `void` | Aumenta +10% |
| `decreaseVolume()` | - | `void` | Disminuye -10% |
| `isPlaying()` | - | `boolean` | ¿Se está reproduciendo? |
| `clearCache()` | - | `void` | Limpia caché de medios |

---

## ✅ Lista de Verificación

Completa esta lista para asegurar que todo está bien:

- [ ] Carpeta `src/main/resources/music/` existe
- [ ] Archivos `menu-theme.mp3`, `game-theme.mp3`, `help-theme.mp3`, `gameover-theme.mp3` están en la carpeta `music/`
- [ ] Archivo `AudioManager.java` está en `src/main/java/com/example/escrituragpt/service/`
- [ ] Todas las clases Stage tienen la importación: `import com.example.escrituragpt.service.AudioManager;`
- [ ] `module-info.java` tiene `requires javafx.media;`
- [ ] `module-info.java` tiene `exports com.example.escrituragpt.service;`
- [ ] `pom.xml` tiene la dependencia `javafx-media`
- [ ] Compilación exitosa: `mvn clean compile` muestra `BUILD SUCCESS`
- [ ] Al ejecutar el juego, escuchas música en el menú
- [ ] Al hacer clic en "Jugar", la música cambia a `game-theme.mp3`

---

## 🎓 Conceptos Clave

### Patrón Singleton
Garantiza que solo exista **una instancia** de `AudioManager` en toda la aplicación.

```java
AudioManager.getInstance()  // Siempre retorna la MISMA instancia
```

### Media Caching
Los archivos se cargan en memoria la **primera vez** y luego se reutilizan:

```java
Map<String, Media> mediaCache = new HashMap<>();
// Primera llamada: carga el archivo
// Segunda llamada: usa la copia en memoria (más rápido)
```

### Reproducción Indefinida
```java
mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
// La música se repite automáticamente
```

---

## 🚨 Notas Importantes

⚠️ **Java 9+ y Módulos:** El proyecto usa módulos Java, por eso necesitamos:
- `requires javafx.media;` en `module-info.java`
- Dependencia en `pom.xml`
- Exports del paquete `service`

⚠️ **Thread Safety:** `AudioManager` es seguro para usar desde el hilo de eventos de JavaFX.

⚠️ **Memoria:** Los archivos se guardan en caché. Para proyectos muy grandes, usa `clearCache()` cuando sea necesario.

---

## 📞 Resumen Rápido

| Tarea | Comando |
|---|---|
| Crear carpeta música | `mkdir -p src/main/resources/music` |
| Compilar proyecto | `.\mvnw.cmd clean compile` |
| Reproducir música | `AudioManager.getInstance().playMusic("menu", "menu-theme.mp3");` |
| Cambiar volumen | `AudioManager.getInstance().setVolume(0.5);` |
| Detener música | `AudioManager.getInstance().stopMusic();` |

---

## 🎉 ¡Listo!

El sistema de música está completamente implementado y funcional. Solo necesitas:

1. **Crear la carpeta** `src/main/resources/music/`
2. **Descargar 4 archivos MP3**
3. **Colocar los archivos** en la carpeta
4. **Compilar y ejecutar**

¡Disfruta de la música de fondo en tu juego! 🎵🎮

---

**Última actualización:** 18 de Marzo de 2026  
**Versión:** 1.0  
**Autor:** EscrituraRápida Team
