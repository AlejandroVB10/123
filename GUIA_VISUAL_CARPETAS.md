# 🎵 GUÍA VISUAL: Dónde Colocar la Música

## 📂 Estructura de Carpetas (Visual)

```
Escrituragpt - copia (Raíz del Proyecto)
│
├── 📁 src/
│   └── 📁 main/
│       ├── 📁 java/
│       │   ├── 📄 module-info.java (MODIFICADO)
│       │   └── 📁 com/example/escrituragpt/
│       │       ├── 📁 service/
│       │       │   └── 📄 AudioManager.java (NUEVO)
│       │       ├── 📁 view/
│       │       ├── 📁 controller/
│       │       └── 📁 model/
│       │
│       └── 📁 resources/
│           ├── 📁 com/example/escrituragpt/  (FXML y CSS)
│           │   ├── menu-view.fxml
│           │   ├── game-view.fxml
│           │   └── ...
│           │
│           └── 📁 music/          ⬅️ CREA ESTA CARPETA
│               ├── 📄 menu-theme.mp3           ⬅️ COLOCA AQUÍ
│               ├── 📄 game-theme.mp3           ⬅️ COLOCA AQUÍ
│               ├── 📄 help-theme.mp3           ⬅️ COLOCA AQUÍ
│               └── 📄 gameover-theme.mp3       ⬅️ COLOCA AQUÍ
│
├── 📁 target/ (Generado automáticamente)
├── 📄 pom.xml (MODIFICADO)
├── 📄 mvnw.cmd
├── 📄 mvnw
│
└── 📄 DOCUMENTACIÓN (NUEVA)
    ├── GUIA_MUSICA_COMPLETA.md
    ├── EJEMPLOS_AUDIO.md
    ├── MUSICA_README.md
    └── RESUMEN_IMPLEMENTACION.md
```

---

## 🖥️ PASO A PASO: Crear Carpeta y Agregar Música

### Opción 1️⃣: Usando Terminal PowerShell

```powershell
# Abre PowerShell en la raíz del proyecto

# 1. Navega al proyecto
cd "C:\Users\USUARIO\IdeaProjects\Escrituragpt - copia"

# 2. Crea la carpeta de música
mkdir -p src\main\resources\music

# 3. Verifica que fue creada
dir src\main\resources\music

# Output esperado:
# Directory: C:\Users\USUARIO\...\Escrituragpt - copia\src\main\resources\music
# Mode                 LastWriteTime         Length Name
# ----                 -------                ------ ----
# (carpeta vacía)
```

### Opción 2️⃣: Usando Explorador de Windows

1. **Abre el Explorador de Archivos**
   - Windows + E

2. **Navega a tu proyecto**
   - Dirección: `C:\Users\USUARIO\IdeaProjects\Escrituragpt - copia`

3. **Abre estas carpetas en orden:**
   - Haz doble clic en `src`
   - Haz doble clic en `main`
   - Haz doble clic en `resources`

4. **Crea una nueva carpeta**
   - Clic derecho en el espacio vacío
   - Selecciona "Nueva carpeta"
   - Nombra la carpeta: `music`

5. **Descarga tus archivos MP3**
   - Descarga 4 archivos MP3 de Incompetech o Pixabay
   - Renómbralos exactamente así:
     - `menu-theme.mp3`
     - `game-theme.mp3`
     - `help-theme.mp3`
     - `gameover-theme.mp3`

6. **Copia los archivos a la carpeta `music`**
   - Abre la carpeta `music` que acabas de crear
   - Pega los 4 archivos aquí
   - Verifica que aparezcan los 4 archivos

---

## ✅ Verificación: ¿Está todo en su lugar?

Ejecuta esto en PowerShell para verificar:

```powershell
# Navega al proyecto
cd "C:\Users\USUARIO\IdeaProjects\Escrituragpt - copia"

# Lista el contenido de la carpeta music
dir src\main\resources\music

# Deberías ver:
# Mode                 LastWriteTime         Length Name
# ----                 -------                ------ ----
# -a---          18/3/2026  15:30       5,234,567 menu-theme.mp3
# -a---          18/3/2026  15:31       4,567,890 game-theme.mp3
# -a---          18/3/2026  15:32       3,456,789 help-theme.mp3
# -a---          18/3/2026  15:33       2,345,678 gameover-theme.mp3
```

Si ves los 4 archivos, ¡todo está correcto! ✅

---

## 🎵 Descargar Música Gratuita

### Opción A: Incompetech (RECOMENDADO)
1. Ve a https://incompetech.com/
2. Busca "royalty free music"
3. Filtra por música instrumental
4. Descargar 4 canciones diferentes

**Sugerencias:**
- Para menú: Busca "ambient" o "calm"
- Para juego: Busca "upbeat" o "energetic"
- Para ayuda: Busca "peaceful" o "gentle"
- Para game over: Busca "sad" o "dramatic"

### Opción B: Pixabay Music
1. Ve a https://pixabay.com/music/
2. Busca por categorías
3. Selecciona música que te guste
4. Descarga (sin registro obligatorio)

### Opción C: YouTube Audio Library
1. Ve a https://www.youtube.com/audiolibrary/
2. Requiere cuenta de Google
3. Acceso a miles de temas gratuitos
4. Descarga en MP3

---

## 📥 Pasos para Descargar (Ejemplo: Incompetech)

1. **Ve a Incompetech**
   - Abre https://incompetech.com/

2. **Busca música**
   - Tipo: "Instrumental"
   - Duración: 1-3 minutos (para que no sea muy largo)

3. **Elige una canción**
   - Haz clic en el nombre
   - Presiona el botón "Download" (MP3)

4. **Guarda en tu carpeta de descargas**
   - Tu navegador descargará el archivo automáticamente

5. **Renombra el archivo**
   - Clic derecho → Renombrar
   - Cambia el nombre a uno de estos:
     - `menu-theme.mp3`
     - `game-theme.mp3`
     - `help-theme.mp3`
     - `gameover-theme.mp3`

6. **Mueve a la carpeta correcta**
   - Abre Explorador
   - Navega a: `Escrituragpt - copia\src\main\resources\music\`
   - Pega el archivo aquí

7. **Repite para los 4 archivos**

---

## 🚀 Compilar y Ejecutar

Una vez que tengas los 4 archivos en su lugar:

```powershell
# En PowerShell, en la raíz del proyecto:

# 1. Compilar
.\mvnw.cmd clean compile

# Espera a que terminecon "BUILD SUCCESS"

# 2. Ejecutar
.\mvnw.cmd javafx:run

# 3. ¡Escucha la música!
```

---

## 🎯 Checklist Final

Antes de compilar, verifica:

- [ ] La carpeta `src/main/resources/music/` existe
- [ ] Tienes 4 archivos MP3 en esa carpeta
- [ ] Los nombres son exactos:
  - [ ] `menu-theme.mp3`
  - [ ] `game-theme.mp3`
  - [ ] `help-theme.mp3`
  - [ ] `gameover-theme.mp3`
- [ ] Los archivos son **exactamente** en formato MP3 (no .mp3.txt)
- [ ] Cada archivo pesa más de 100 KB (no están vacíos)

---

## 🔍 Troubleshooting: Si algo No Funciona

### ❌ "Music file not found"

**Causa 1:** La carpeta no está donde debería  
**Solución:** Verifica que está en `src/main/resources/music/`

**Causa 2:** El nombre del archivo no es exacto  
**Solución:** Comprueba mayúsculas/minúsculas exactamente

**Causa 3:** El archivo descargado es de otro formato  
**Solución:** Convierte a MP3 usando CloudConvert

### ❌ No escuchas nada

**Causa 1:** El volumen del sistema está bajo  
**Solución:** Aumenta el volumen de Windows

**Causa 2:** Los parlantes no están encendidos  
**Solución:** Verifica conexión de audio

**Causa 3:** Java no puede acceder a los archivos  
**Solución:** Reinicia el IDE y vuelve a compilar

---

## 📊 Tamaño Esperado de Archivos

| Archivo | Duración | Tamaño Típico |
|---------|----------|---------------|
| menu-theme.mp3 | 2:30 | 2.5 MB |
| game-theme.mp3 | 2:00 | 2.0 MB |
| help-theme.mp3 | 1:30 | 1.5 MB |
| gameover-theme.mp3 | 1:00 | 1.0 MB |

**Total:** ~7 MB (perfectamente razonable)

---

## 💾 Alternativa: Usar Archivos Temporales

Si no puedes descargar ahora, puedes crear archivos vacíos para probar:

```powershell
# En PowerShell (crea archivos dummy de prueba):

$musicPath = "src\main\resources\music"

# Crear archivos vacíos
New-Item "$musicPath\menu-theme.mp3" -ItemType File -Force
New-Item "$musicPath\game-theme.mp3" -ItemType File -Force
New-Item "$musicPath\help-theme.mp3" -ItemType File -Force
New-Item "$musicPath\gameover-theme.mp3" -ItemType File -Force

# Verifica
dir "$musicPath"
```

⚠️ **Nota:** Los archivos vacíos no se reproducirán, pero te permiten compilar y probar.

---

## 🎬 Video Tutorial Alternativo

Si prefieres ver un video:
- Busca en YouTube: "JavaFX MediaPlayer tutorial"
- Los conceptos son los mismos

---

## 📞 Resumen Ultra-Rápido

```
1. mkdir src\main\resources\music
2. Descarga 4 MP3 de incompetech.com
3. Renombra exactamente (sin espacios, sin mayúsculas raras)
4. Coloca en src\main\resources\music\
5. .\mvnw.cmd clean compile
6. .\mvnw.cmd javafx:run
7. ¡Disfruta!
```

---

## 🎉 ¡Listo!

Una vez que tengas los archivos en su lugar, la música debería funcionar automáticamente. No hay nada más que hacer en el código.

**Recuerda:** Los cambios en `resources/` se cargan automáticamente sin necesidad de recompilar todo.

---

**Última actualización:** 18 de Marzo de 2026  
**Versión:** 1.0  
**Estado:** LISTO PARA USAR

🎵 **¡Que disfrutes la música!** 🎮
