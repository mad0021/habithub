# HabitHub - Aplicación de Gestión de Objetivos Mensuales

[![CI](https://github.com/mad0021/habithub/actions/workflows/android-ci.yml/badge.svg)](https://github.com/mad0021/habithub/actions/workflows/android-ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Android-26%2B-green.svg)](https://android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-blue.svg)](https://kotlinlang.org)

## 📱 Descripción
HabitHub es una aplicación Android moderna para la gestión de objetivos mensuales y notas diarias. Permite organizar tus metas, trackear tu progreso, añadir notas en el calendario y visualizar tus estadísticas con gráficos elegantes.

## ✨ Características Principales

### 📅 Calendario Mensual (MonthlyCalendarScreen)
- **Visualización mensual completa**: Calendario interactivo con todos los días del mes
- **Notas diarias**: Añade y edita notas en cualquier día
- **Navegación fluida**: Cambia entre meses fácilmente
- **Interfaz intuitiva**: Diseño limpio y fácil de usar

### 🎯 Tabla de Objetivos (ObjectivesTableScreen)
- **Gestión de objetivos mensuales**: Crea y organiza tus metas del mes
- **Seguimiento de completado**: Marca objetivos como completados
- **Estado visual**: Colores y estados claros para cada objetivo
- **Edición rápida**: Modifica o elimina objetivos fácilmente

### 📊 Gráficos de Progreso (ProgressChartScreen)
- **Visualización de datos**: Gráficos elegantes con Vico Charts
- **Estadísticas detalladas**: Porcentajes de completado y tendencias
- **Análisis temporal**: Ve tu progreso a lo largo del tiempo
- **Diseño Material3**: Gráficos modernos y animados

### ⚙️ Configuración (SettingsScreen)
- **Cambio de idioma**: Español e Inglés disponibles
- **Tema OLED**: Modo claro y oscuro (optimizado para OLED)
- **Preferencias persistentes**: Configuración guardada con DataStore
- **Interfaz responsive**: Adaptable a diferentes tamaños de pantalla

## 🛠️ Stack Tecnológico

### Framework y Lenguaje

- **Kotlin 2.0.21**: Lenguaje de programación moderno y seguro
- **Jetpack Compose**: UI declarativa con Compose BOM 2024.12.01
- **Material Design 3**: Material3 1.4.0-alpha03 con Adaptive y Material Icons

### Arquitectura

- **MVVM** (Model-View-ViewModel) con Clean Architecture
- **Hilt 2.46.1**: Inyección de dependencias de Dagger
- **Room 2.6.0**: Persistencia de datos local con Flow
- **Kotlin Coroutines**: Operaciones asíncronas
- **DataStore 1.1.1**: Almacenamiento de preferencias

### Bibliotecas Principales

- **Navigation Compose 2.8.5**: Navegación entre pantallas
- **Vico Charts 1.13.1**: Gráficos elegantes y animados
- **Coil 2.7.0**: Carga de imágenes y GIFs
- **Lifecycle ViewModel 2.8.7**: Gestión del ciclo de vida
- **AppCompat 1.7.0**: Soporte de localización

### Herramientas de Calidad

- **ktlint 12.1.2**: Análisis de estilo de código
- **Detekt 1.23.7**: Análisis estático
- **Dependency Updates 0.51.0**: Gestión de actualizaciones
- **MockK 1.14.4**: Testing framework

## 📦 Estructura del Proyecto

```kotlin
HabitHub/
├── app/src/main/java/com/dennnisver4/habithub/
│   ├── data/                          # Capa de datos
│   │   ├── DailyNote.kt              # Entity: Notas diarias
│   │   ├── MonthlyObjective.kt       # Entity: Objetivos mensuales
│   │   ├── ObjectiveCompletion.kt    # Entity: Completado de objetivos
│   │   ├── HabitHubDao.kt           # DAO: Operaciones de BD
│   │   ├── HabitHubDatabase.kt      # Room Database
│   │   ├── ThemePreferences.kt       # DataStore: Preferencias
│   │   └── repository/               # Repositorios
│   │       ├── MonthlyCalendarRepository.kt
│   │       ├── ObjectivesRepository.kt
│   │       └── ProgressRepository.kt
│   ├── di/                           # Inyección de dependencias
│   │   └── AppModule.kt             # Módulo Hilt
│   ├── ui/                           # Capa de presentación
│   │   ├── screens/                  # Pantallas Compose
│   │   │   ├── MonthlyCalendarScreen.kt
│   │   │   ├── ObjectivesTableScreen.kt
│   │   │   ├── ProgressChartScreen.kt
│   │   │   └── SettingsScreen.kt
│   │   ├── viewmodel/               # ViewModels
│   │   │   ├── MonthlyCalendarViewModel.kt
│   │   │   ├── ObjectivesViewModel.kt
│   │   │   └── ProgressViewModel.kt
│   │   ├── navigation/              # Navegación
│   │   │   └── Screen.kt
│   │   └── theme/                   # Tema Material3
│   │       ├── Color.kt
│   │       ├── Shape.kt
│   │       ├── Theme.kt
│   │       └── Type.kt
│   ├── HabitHubApplication.kt       # @HiltAndroidApp
│   ├── MainActivity.kt              # @AndroidEntryPoint
│   └── SplashActivity.kt            # Pantalla de inicio
├── app/src/main/res/
│   ├── values/                      # Recursos español (default)
│   │   ├── strings.xml
│   │   ├── colors.xml
│   │   └── themes.xml
│   └── values-en/                   # Recursos inglés
│       └── strings.xml
├── .github/workflows/               # CI/CD
│   ├── android-ci.yml              # Pipeline principal
│   ├── release.yml                 # Releases automáticos
│   ├── code-quality.yml            # Análisis de calidad
│   └── dependabot-auto-merge.yml   # Auto-merge
├── config/detekt/                   # Configuración Detekt
│   ├── detekt.yml
│   └── baseline.xml
├── .editorconfig                    # Configuración editor
├── CONTRIBUTING.md                  # Guía de contribución
├── LICENSE                          # MIT License
└── README.md                        # Este archivo
```

## 🚀 Requisitos y Configuración

### Requisitos del Sistema

- **Android Studio**: Ladybug 2024.2.1 o superior
- **JDK**: 11 (local) / 17 (CI/CD)
- **Android SDK**: 36
- **Gradle**: 8.13.0
- **AGP**: 8.13.0

### Configuración del Proyecto

- **Package**: `com.dennnisver4.habithub`
- **minSdk**: 26 (Android 8.0 Oreo)
- **targetSdk**: 36 (Android 14+)
- **compileSdk**: 36
- **versionCode**: 1
- **versionName**: "1.0"

### Pasos de Instalación

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/mad0021/habithub.git
   cd habithub
   ```

2. **Abrir en Android Studio**:
   - `File` > `Open` > Selecciona la carpeta del proyecto
   - Espera a que Gradle sincronice automáticamente

3. **Compilar el proyecto**:

   ```bash
   ./gradlew assembleDebug
   ```

   O desde Android Studio: `Build` > `Make Project` (`Ctrl + F9`)

4. **Ejecutar la aplicación**:
   - Conecta un dispositivo Android (API 26+) o inicia un emulador
   - `Run` > `Run 'app'` (`Shift + F10`)

## � Base de Datos

HabitHub utiliza Room Database para almacenamiento local persistente.

### Esquema de Base de Datos

**daily_notes**

```sql
CREATE TABLE daily_notes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    date TEXT NOT NULL,
    note TEXT NOT NULL
);
```

**monthly_objectives**

```sql
CREATE TABLE monthly_objectives (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    description TEXT,
    month INTEGER NOT NULL,
    year INTEGER NOT NULL,
    created_at INTEGER NOT NULL
);
```

**objective_completions**

```sql
CREATE TABLE objective_completions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    objective_id INTEGER NOT NULL,
    date TEXT NOT NULL,
    completed INTEGER NOT NULL,
    FOREIGN KEY(objective_id) REFERENCES monthly_objectives(id) ON DELETE CASCADE
);
```

### Repositorios

- **MonthlyCalendarRepository**: Gestión de notas diarias en el calendario
- **ObjectivesRepository**: CRUD de objetivos mensuales
- **ProgressRepository**: Estadísticas y datos para gráficos

## � Localización

La aplicación soporta múltiples idiomas:

- 🇪🇸 **Español** (por defecto)
- 🇬🇧 **Inglés**

El idioma se selecciona automáticamente según la configuración del sistema. Los usuarios pueden cambiarlo manualmente en la pantalla de Configuración.

### Archivos de Localización

- `res/values/strings.xml` - Español (60 strings)
- `res/values-en/strings.xml` - Inglés (60 strings)
- `res/xml/locales_config.xml` - Configuración de locales

## 🎨 Temas

### OLED Dark Theme

HabitHub incluye un tema oscuro optimizado para pantallas OLED:

- **Modo Claro**: Colores vibrantes con fondo blanco
- **Modo Oscuro OLED**: Negro puro (#000000) para ahorro de batería

Las preferencias de tema se guardan con DataStore y persisten entre sesiones.

## 🐛 Solución de Problemas

### Error de compilación Gradle

```bash
# Limpiar y reconstruir
./gradlew clean
./gradlew build --refresh-dependencies
```

### Error de Hilt

Verifica que las anotaciones estén correctas:

- `@HiltAndroidApp` en `HabitHubApplication`
- `@AndroidEntryPoint` en `MainActivity` y `SplashActivity`
- `@HiltViewModel` en todos los ViewModels
- `@Inject` en constructores de repositorios

### Error de kapt

```bash
# Invalidar cachés en Android Studio
File > Invalidate Caches > Invalidate and Restart
```

### Los gráficos no se muestran

- Verifica que tengas datos en el rango de fechas seleccionado
- Revisa que Vico Charts esté correctamente importado en `libs.versions.toml`

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

## 📊 Estado del Proyecto

| Característica | Estado | Detalles |
|----------------|--------|----------|
| MVVM Architecture | ✅ **Completado** | 3 ViewModels con @HiltViewModel |
| Hilt DI | ✅ **Completado** | Inyección de dependencias configurada |
| Room Database | ✅ **Completado** | 3 entidades, 1 DAO, 3 repositorios |
| Localization (ES/EN) | ✅ **Completado** | 60 strings en cada idioma |
| OLED Dark Theme | ✅ **Completado** | Tema oscuro optimizado para OLED |
| Material Design 3 | ✅ **Completado** | Material3 1.4.0-alpha03 |
| CI/CD Pipeline | ✅ **Completado** | 4 workflows de GitHub Actions |
| Code Quality Tools | ✅ **Completado** | ktlint, Detekt, Dependency Updates |
| Splash Screen | ✅ **Completado** | SplashActivity con branding |
| Navigation | ✅ **Completado** | Navigation Compose con 4 pantallas |
| Unit Tests | 🚧 **Pendiente** | MockK configurado, tests por implementar |
| Widget Home Screen | 🔜 **Futuro** | Planificado |
| Cloud Sync | 🔜 **Futuro** | Planificado |
| Notifications | 🔜 **Futuro** | Planificado |

## 🎯 Roadmap

### v1.1.0 (Próximo)

- [ ] Tests unitarios para ViewModels (70%+ cobertura)
- [ ] Tests de integración para Repositories
- [ ] Tests UI para pantallas críticas
- [ ] Documentación de arquitectura completa

### v1.2.0 (Futuro)

- [ ] Widget de inicio con objetivos del día
- [ ] Notificaciones de recordatorio
- [ ] Exportar/importar datos (JSON)
- [ ] Racha de días consecutivos

### v2.0.0 (Largo plazo)

- [ ] Sincronización en la nube (Firebase)
- [ ] Categorías de objetivos
- [ ] Estadísticas avanzadas
- [ ] Modo tablet con diseño adaptativo
- [ ] Wear OS companion app

## 📄 Licencia
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
