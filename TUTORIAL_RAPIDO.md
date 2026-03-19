# 🎬 TUTORIAL RÁPIDO: Desde Cero Hasta Música Funcionando

## ⏱️ Tiempo estimado: 20 minutos

Este tutorial te guía paso a paso para tener música funcionando en tu juego.

---

## PASO 1: Abrir PowerShell (2 minutos)

### En Windows:
1. Presiona **Windows + X**
2. Selecciona **Windows PowerShell (Admin)**
3. O busca "PowerShell" en el menú inicio

### Deberías ver algo como:
```
PS C:\Users\USUARIO>
```

---

## PASO 2: Navegar al Proyecto (1 minuto)

Copia y pega esto en PowerShell:

```powershell
cd "C:\Users\USUARIO\IdeaProjects\Escrituragpt - copia"
```

Presiona **ENTER**

Verifica que estés en el lugar correcto:

```powershell
dir
```

Deberías ver carpetas como: `src`, `target`, `mvnw.cmd`, etc.

---

## PASO 3: Crear la Carpeta de Música (2 minutos)

Copia y pega esto:

```powershell
mkdir src\main\resources\music
```

Presiona **ENTER**

Verifica que se creó:

```powershell
dir src\main\resources\music
```

Deberías ver una carpeta vacía (sin archivos todavía).

---

## PASO 4: Descargar Música (10 minutos)

### Opción A: Incompetech (RECOMENDADO)

1. **Abre tu navegador** (Chrome, Firefox, Edge)
2. **Ve a:** https://incompetech.com/
3. **Busca música:**
   - Tipo: "Royalty Free Music"
   - Filtra por "Ambient" o "Calm"
4. **Descarga 4 temas diferentes:**
   - 1 relajante (para menú)
   - 1 energético (para juego)
   - 1 suave (para ayuda)
   - 1 dramático (para game over)

### Pasos para descargar:

1. Haz clic en el nombre de la canción
2. Busca el botón **"Download"** (MP3)
3. Elige guardar en tu carpeta de **Descargas**
4. Espera a que termine la descarga

---

## PASO 5: Renombrar Archivos (3 minutos)

### Usando Explorador de Windows:

1. **Abre Explorador** (Windows + E)
2. **Ve a Descargas** (carpeta donde descargaste los MP3)
3. **Para cada archivo:**
   - Clic derecho en el archivo
   - Selecciona "Renombrar"
   - Cambia el nombre exactamente a:

| # | Renombra a |
|---|-----------|
| 1 | `menu-theme.mp3` |
| 2 | `game-theme.mp3` |
| 3 | `help-theme.mp3` |
| 4 | `gameover-theme.mp3` |

⚠️ **IMPORTANTE:** Debe ser exacto (sin mayúsculas extras, sin espacios al final)

---

## PASO 6: Copiar Archivos a la Carpeta Correcta (2 minutos)

### Usando Explorador:

1. **Abre Explorador** (Windows + E)
2. **Ve a Descargas** (donde están los 4 archivos renombrados)
3. **Selecciona los 4 archivos:**
   - Clic en el primero
   - Ctrl + clic en los otros 3
4. **Copia (Ctrl + C)**
5. **Ve a la carpeta correcta:**
   - `C:\Users\USUARIO\IdeaProjects\Escrituragpt - copia\src\main\resources\music\`
6. **Pega (Ctrl + V)**

### O usando PowerShell:

```powershell
# Copia los 4 archivos desde Descargas
Copy-Item "$env:USERPROFILE\Downloads\menu-theme.mp3" "src\main\resources\music\"
Copy-Item "$env:USERPROFILE\Downloads\game-theme.mp3" "src\main\resources\music\"
Copy-Item "$env:USERPROFILE\Downloads\help-theme.mp3" "src\main\resources\music\"
Copy-Item "$env:USERPROFILE\Downloads\gameover-theme.mp3" "src\main\resources\music\"
```

### Verifica que se copiaron:

```powershell
dir src\main\resources\music
```

Deberías ver:

```
    Directory: C:\...\src\main\resources\music

Mode                 LastWriteTime         Length Name
----                 -------                ------ ----
-a---          18/3/2026  15:30       5,234,567 menu-theme.mp3
-a---          18/3/2026  15:31       4,567,890 game-theme.mp3
-a---          18/3/2026  15:32       3,456,789 help-theme.mp3
-a---          18/3/2026  15:33       2,345,678 gameover-theme.mp3
```

✅ Si ves los 4 archivos, ¡estás en el camino correcto!

---

## PASO 7: Compilar el Proyecto (3 minutos)

En PowerShell (en la carpeta raíz), escribe:

```powershell
.\mvnw.cmd clean compile
```

Presiona **ENTER** y espera...

### Qué verás:

```
[INFO] Building Escrituragpt 1.0-SNAPSHOT
[INFO] ....
[INFO] BUILD SUCCESS
```

✅ Si ves "BUILD SUCCESS", ¡funciona!

❌ Si ves "BUILD FAILURE", lee el error en GUIA_MUSICA_COMPLETA.md

---

## PASO 8: Ejecutar el Juego (1 minuto)

En PowerShell, escribe:

```powershell
.\mvnw.cmd javafx:run
```

Presiona **ENTER**

### Qué pasará:

1. Se abrirá una ventana con el juego
2. **DEBERÍAS ESCUCHAR MÚSICA** 🎵
3. Si no escuchas nada, verifica:
   - Volumen del sistema (no silenciado)
   - Parlantes conectados
   - Archivos MP3 en la carpeta correcta

---

## PASO 9: Verificar Que Funciona (2 minutos)

En la ventana del juego:

1. ✅ Abre el menú → ¿Escuchas `menu-theme.mp3`?
2. ✅ Haz clic en "Jugar" → ¿Cambia la música a `game-theme.mp3`?
3. ✅ Termina el juego → ¿Cambia a `gameover-theme.mp3`?
4. ✅ Vuelve al menú → ¿Vuelve `menu-theme.mp3`?

Si todo funciona = ¡ÉXITO! 🎉

---

## ¡LISTO! 🎵

Acabas de:
1. ✅ Crear la carpeta de música
2. ✅ Descargar 4 archivos MP3
3. ✅ Renombrar correctamente
4. ✅ Copiar a la ubicación correcta
5. ✅ Compilar el proyecto
6. ✅ Ejecutar con música automática

**Tiempo total:** ~20 minutos (depende de la velocidad de internet)

---

## Comandos Resumidos (Copia y Pega)

Si quieres hacerlo todo de una vez:

```powershell
# 1. Ir al proyecto
cd "C:\Users\USUARIO\IdeaProjects\Escrituragpt - copia"

# 2. Crear carpeta
mkdir src\main\resources\music

# 3. Compilar
.\mvnw.cmd clean compile

# 4. Ejecutar
.\mvnw.cmd javafx:run
```

⚠️ **Nota:** Los pasos 1-2 son para la primera vez. 
Los pasos 3-4 puedes repetirlos después.

---

## Solución Rápida de Problemas

### "Music file not found"
✅ Verifica: `dir src\main\resources\music`

### No escuchas música
✅ Sube el volumen del sistema

### Error de compilación
✅ Compila nuevamente: `.\mvnw.cmd clean compile`

### ¿Necesitas ayuda?
✅ Lee GUIA_MUSICA_COMPLETA.md (sección Troubleshooting)

---

## Próximos Pasos (Opcional)

Ahora que tienes música funcionando:

1. **Personalizar música:**
   - Cambia los archivos por otros
   - O edita 1 línea en cada Stage class

2. **Agregar controles:**
   - Lee EJEMPLOS_AUDIO.md
   - Agrega slider de volumen

3. **Efectos de sonido:**
   - Sonidos para acciones correctas/incorrectas
   - Sonidos para transiciones

---

## ¡Disfruta tu juego con música! 🎮🎵

Versión: 1.0  
Fecha: 18 de Marzo de 2026  
Tiempo estimado: 20 minutos
