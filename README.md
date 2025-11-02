# HabitHub - Aplicación de Seguimiento de Hábitos

[![CI](https://github.com/dennnisver4/HabitHub/actions/workflows/android-ci.yml/badge.svg)](https://github.com/dennnisver4/HabitHub/actions/workflows/android-ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Android-26%2B-green.svg)](https://android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-blue.svg)](https://kotlinlang.org)

## 📱 Descripción
HabitHub es una aplicación Android moderna para el seguimiento de hábitos personales y disciplina. Permite trackear rutinas diarias, visualizar tu progreso a través del tiempo y mantener la motivación con estadísticas detalladas.

## ✨ Características

### 🏠 Pantalla Principal
- **Calendario horizontal**: Navega fácilmente entre días para marcar tus hábitos
- **Vista de hábitos**: Lista de todos tus hábitos activos
- **Marcado rápido**: Checkbox para marcar hábitos como completados
- **Personalización**: Cada hábito tiene su propio color e icono

### ➕ Gestión de Hábitos
- **Crear nuevos hábitos**: Añade hábitos con nombre, descripción, color e icono personalizados
- **12 colores predefinidos**: Elige entre una paleta vibrante de colores
- **24 iconos emoji**: Selecciona el icono perfecto para tu hábito
- **Editar y eliminar**: Administra tus hábitos fácilmente

### 📊 Estadísticas y Gráficos
Visualiza tu progreso en diferentes periodos de tiempo:
- **Semana**: Los últimos 7 días
- **Mes**: Los últimos 30 días
- **6 Meses**: Los últimos 6 meses
- **Año**: El último año
- **2 Años**: Los últimos 2 años

Para cada hábito se muestra:
- Días completados vs. días totales
- Tasa de cumplimiento (%)
- Barra de progreso visual
- Gráfico de tendencia temporal

## 🛠️ Tecnologías Utilizadas

### Framework y Lenguaje
- **Kotlin**: Lenguaje de programación moderno
- **Jetpack Compose**: UI declarativa moderna
- **Material Design 3**: Diseño actualizado y elegante

### Arquitectura
- **MVVM** (Model-View-ViewModel)
- **Room Database**: Persistencia de datos local
- **Kotlin Flows**: Programación reactiva
- **Coroutines**: Operaciones asíncronas

### Bibliotecas Principales
- **AndroidX Navigation**: Navegación entre pantallas
- **Room**: Base de datos SQLite con abstracción
- **Vico Charts**: Gráficos elegantes y animados
- **Lifecycle ViewModel**: Gestión del ciclo de vida

## 📦 Estructura del Proyecto

```
app/src/main/java/com/dennnisver4/habithub/
├── data/
│   ├── model/
│   │   ├── Habit.kt              # Modelo de datos de hábito
│   │   ├── HabitEntry.kt         # Registro diario de hábito
│   │   └── HabitWithEntries.kt   # Relación entre hábito y registros
│   ├── dao/
│   │   └── HabitDao.kt           # Operaciones de base de datos
│   ├── database/
│   │   └── HabitDatabase.kt      # Configuración de Room
│   └── repository/
│       └── HabitRepository.kt    # Capa de acceso a datos
├── ui/
│   ├── screens/
│   │   ├── HomeScreen.kt         # Pantalla principal con calendario
│   │   ├── AddHabitScreen.kt     # Pantalla para añadir hábitos
│   │   └── StatisticsScreen.kt   # Pantalla de estadísticas
│   ├── viewmodel/
│   │   └── HabitViewModel.kt     # Lógica de negocio
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── navigation/
│       └── Screen.kt             # Rutas de navegación
├── utils/
│   └── DateUtils.kt              # Utilidades de fecha
└── MainActivity.kt               # Actividad principal
```

## 🚀 Instalación y Compilación

### Requisitos Previos
- Android Studio Narwhal 4 Feature Drop | 2025.1.4 o superior
- JDK 11 o superior
- Android SDK 36
- Gradle 8.13.0

### Pasos de Instalación

1. **Clonar o abrir el proyecto** en Android Studio

2. **Sincronizar Gradle**:
   - Android Studio sincronizará automáticamente las dependencias
   - Si no lo hace, haz clic en `File` > `Sync Project with Gradle Files`

3. **Compilar el proyecto**:
   - Haz clic en `Build` > `Make Project`
   - O presiona `Ctrl + F9` (Windows/Linux) / `Cmd + F9` (Mac)

4. **Ejecutar la aplicación**:
   - Conecta un dispositivo Android o inicia un emulador
   - Haz clic en el botón `Run` (▶️) o presiona `Shift + F10`

## 📱 Uso de la Aplicación

### Añadir un Hábito
1. Toca el botón flotante **+** en la pantalla principal
2. Ingresa el nombre del hábito (obligatorio)
3. Añade una descripción (opcional)
4. Selecciona un color
5. Elige un icono
6. Toca **"Guardar Hábito"**

### Marcar un Hábito como Completado
1. En la pantalla principal, navega al día deseado usando el calendario horizontal
2. Toca el checkbox junto al hábito para marcarlo como completado
3. El estado se guarda automáticamente

### Ver Estadísticas
1. Toca el icono de gráficos (📊) en la barra superior
2. Selecciona el periodo de tiempo que deseas analizar
3. Revisa las estadísticas de cada hábito:
   - Días completados
   - Tasa de cumplimiento
   - Gráfico de tendencia

### Eliminar un Hábito
1. Toca el icono de papelera (🗑️) junto al hábito
2. Confirma la eliminación en el diálogo
3. El hábito y todos sus registros se eliminarán permanentemente

## 🎨 Personalización

### Colores Disponibles
La aplicación incluye 12 colores predefinidos:
- Púrpura (#6200EE)
- Turquesa (#03DAC5)
- Rosa (#FF0266)
- Naranja Profundo (#FF5722)
- Verde (#4CAF50)
- Azul (#2196F3)
- Ámbar (#FFC107)
- Morado (#9C27B0)
- Rosa Intenso (#E91E63)
- Cian (#00BCD4)
- Verde Lima (#8BC34A)
- Naranja (#FF9800)

### Iconos Disponibles
24 emojis representativos:
⭐ 💪 🏃 📚 🧘 💻 🎯 🔥 ✅ 🎨 🎵 🍎
💤 🚴 🏋️ 🧠 📝 ⚡ 🌟 🎓 ☕ 🌱 🏆 💡

## 💾 Persistencia de Datos

Los datos se almacenan localmente en el dispositivo usando Room Database. Todos los hábitos y sus registros persisten entre sesiones de la aplicación.

### Estructura de la Base de Datos

**Tabla: habits**
- `id`: Identificador único
- `name`: Nombre del hábito
- `description`: Descripción opcional
- `color`: Color en formato hexadecimal
- `icon`: Emoji del hábito
- `createdAt`: Timestamp de creación
- `isActive`: Estado del hábito

**Tabla: habit_entries**
- `id`: Identificador único
- `habitId`: Referencia al hábito
- `date`: Fecha en formato yyyy-MM-dd
- `completed`: Estado de completado
- `notes`: Notas opcionales

## 🐛 Solución de Problemas

### La aplicación no compila
1. Verifica que tengas instalado JDK 11
2. Sincroniza Gradle: `File` > `Sync Project with Gradle Files`
3. Limpia el proyecto: `Build` > `Clean Project`
4. Reconstruye: `Build` > `Rebuild Project`

### Error de kapt
1. Asegúrate de tener el plugin `kotlin-kapt` en `build.gradle.kts`
2. Invalida cachés: `File` > `Invalidate Caches` > `Invalidate and Restart`

### Los gráficos no se muestran
1. Verifica que la biblioteca Vico esté correctamente importada
2. Asegúrate de tener datos en el periodo seleccionado

## � CI/CD y Workflows

### GitHub Actions

El proyecto incluye workflows automatizados profesionales:

#### 🔨 CI Pipeline (`android-ci.yml`)
Se ejecuta en cada push y pull request:
- ✅ Build del proyecto
- ✅ Tests unitarios y de integración
- ✅ Análisis de código con ktlint
- ✅ Android Lint
- ✅ Tests instrumentados (en PRs)
- ✅ Reporte de cobertura de tests
- ✅ Generación de APK debug

#### 📦 Release Pipeline (`release.yml`)
Se activa con tags `v*.*.*`:
- 📦 Build de APK release
- 🔐 Firmado de APK (si está configurado)
- 📝 Generación automática de changelog
- 🚀 Creación de GitHub Release
- 📤 Subida de artifacts

#### 🔍 Code Quality (`code-quality.yml`)
Se ejecuta en pull requests:
- 🔎 Análisis estático con Detekt
- 📊 Análisis de complejidad de código
- ☁️ Integración con SonarCloud (opcional)
- 📈 Verificación de actualizaciones de dependencias

#### 🤖 Dependabot
- 📦 Actualizaciones automáticas de Gradle
- 🔄 Actualizaciones de GitHub Actions
- ✅ Auto-merge de parches seguros

### Calidad de Código

```bash
# Verificar estilo de código
./gradlew ktlintCheck

# Auto-formatear código
./gradlew ktlintFormat

# Análisis estático
./gradlew detekt

# Android Lint
./gradlew lint

# Verificar actualizaciones de dependencias
./gradlew dependencyUpdates
```

### Crear un Release

```bash
# 1. Asegúrate de estar en main y actualizado
git checkout main
git pull origin main

# 2. Crea y empuja un tag
git tag -a v1.0.0 -m "Release v1.0.0: Descripción de cambios"
git push origin v1.0.0

# 3. GitHub Actions automáticamente:
#    - Compila el APK release
#    - Crea el GitHub Release
#    - Genera el changelog
#    - Sube el APK como artifact
```

## 🧪 Testing

### Ejecutar Tests Localmente

```bash
# Tests unitarios
./gradlew test

# Tests instrumentados (requiere emulador o dispositivo)
./gradlew connectedAndroidTest

# Todos los checks (tests + lint)
./gradlew check

# Con reporte de cobertura
./gradlew testDebugUnitTest jacocoTestReport
```

### Cobertura de Tests

Los reportes se generan en:
- Tests unitarios: `app/build/reports/tests/testDebugUnitTest/index.html`
- Android Lint: `app/build/reports/lint-results.html`
- Detekt: `build/reports/detekt/detekt.html`
- Cobertura: `app/build/reports/jacoco/html/index.html`

## 🤝 Contribuir

¡Las contribuciones son bienvenidas! Consulta [CONTRIBUTING.md](CONTRIBUTING.md) para más detalles.

### Proceso de Contribución

1. **Fork** el repositorio
2. **Crea** una rama: `git checkout -b feature/AmazingFeature`
3. **Commit** con Conventional Commits: `git commit -m 'feat: Add AmazingFeature'`
4. **Push**: `git push origin feature/AmazingFeature`
5. **Abre** un Pull Request

### Conventional Commits

Seguimos [Conventional Commits](https://www.conventionalcommits.org/):

- `feat:` Nueva funcionalidad
- `fix:` Corrección de bug
- `docs:` Cambios en documentación
- `style:` Formato, espacios (sin cambios de código)
- `refactor:` Refactorización de código
- `test:` Añadir o modificar tests
- `chore:` Mantenimiento, dependencias
- `ci:` Cambios en CI/CD

**Ejemplo:**
```
feat(calendar): add swipe gesture to navigate months

- Added swipe left/right to change months
- Improved animation transitions
- Updated tests

Closes #123
```

## �🔮 Futuras Mejoras

- [ ] Recordatorios y notificaciones
- [ ] Widgets para la pantalla de inicio
- [ ] Racha de días consecutivos
- [ ] Exportar/importar datos
- [ ] ✅ ~~Temas personalizados (modo oscuro OLED)~~ ✅ Completado
- [ ] Categorías de hábitos
- [ ] Objetivos semanales/mensuales
- [ ] Sincronización en la nube
- [ ] ✅ ~~CI/CD completo~~ ✅ Completado
- [ ] Tests unitarios (70%+ cobertura)

## � Estado del Proyecto

| Característica | Estado |
|----------------|--------|
| MVVM Architecture | ✅ Implementado |
| Hilt DI | ✅ Implementado |
| Room Database | ✅ Implementado |
| Localization (ES/EN) | ✅ Implementado |
| OLED Dark Theme | ✅ Implementado |
| Material Design 3 | ✅ Implementado |
| CI/CD Pipeline | ✅ Implementado |
| Code Quality Tools | ✅ Implementado |
| Unit Tests | 🚧 En progreso |
| Widget Home Screen | 🔜 Próximamente |
| Cloud Sync | 🔜 Próximamente |

##  Licencia

Este proyecto está bajo la [Licencia MIT](LICENSE). Esto significa que puedes:

- ✅ Usar el código libremente en proyectos personales y comerciales
- ✅ Modificar y adaptar el código a tus necesidades
- ✅ Distribuir el código original o modificado
- ✅ Usar el código en aplicaciones privadas

La única condición es mantener el aviso de copyright y la licencia en las copias del software.

## 👨‍💻 Desarrollador

**Dennis Ver**

- GitHub: [@dennnisver4](https://github.com/dennnisver4)
- Email: [contacto@realdavidbb@gmail.com](mailto:realdavidbb@gmail.com)

Desarrollado con ❤️ usando Android Studio y las últimas tecnologías de Android.

## 🙏 Agradecimientos

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - UI moderna declarativa
- [Hilt](https://dagger.dev/hilt/) - Inyección de dependencias
- [Room](https://developer.android.com/training/data-storage/room) - Base de datos local
- [Vico Charts](https://github.com/patrykandpatrick/vico) - Gráficas elegantes
- [Material Design 3](https://m3.material.io/) - Sistema de diseño
- [GitHub Actions](https://github.com/features/actions) - CI/CD automatizado

---

<div align="center">

**⭐ Si te gusta HabitHub, dale una estrella ⭐**

**¡Empieza hoy mismo a construir mejores hábitos! 💪🎯**

Made with ❤️ and ☕

</div>
