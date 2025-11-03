# HabitHub - Monthly Objectives Management Application

[![CI](https://github.com/mad0021/habithub/actions/workflows/android-ci.yml/badge.svg)](https://github.com/mad0021/habithub/actions/workflows/android-ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Android-26%2B-green.svg)](https://android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-blue.svg)](https://kotlinlang.org)
[![Privacy](https://img.shields.io/badge/Privacy-100%25%20Offline-success.svg)](PRIVACY_POLICY.md)
[![Security](https://img.shields.io/badge/Security-No%20Permissions-blue.svg)](SECURITY.md)

## 📱 Overview

HabitHub is a modern Android application for managing monthly objectives and daily notes. It enables you to organize your goals, track your progress, add calendar notes, and visualize your statistics with elegant charts.

## 🔒 Privacy and Security

**HabitHub is 100% private and secure:**

- ✅ **No Internet**: Completely offline application
- ✅ **No Permissions**: Does not request dangerous permissions
- ✅ **No Data Collection**: Zero telemetry or analytics
- ✅ **No Advertisements**: Ad-free experience
- ✅ **No Cloud Backups**: Your data remains ONLY on your device
- ✅ **Open Source**: Auditable and transparent

📄 Read our complete [Privacy Policy](PRIVACY_POLICY.md)

🔐 Review our [Security Practices](SECURITY.md)

## ✨ Key Features

### 📅 Monthly Calendar (MonthlyCalendarScreen)
- **Complete monthly view**: Interactive calendar displaying all days of the month
- **Daily notes**: Add and edit notes on any day
- **Fluid navigation**: Easily switch between months
- **Intuitive interface**: Clean and user-friendly design

### 🎯 Objectives Table (ObjectivesTableScreen)
- **Monthly objectives management**: Create and organize your monthly goals
- **Completion tracking**: Mark objectives as completed
- **Visual status**: Clear colors and states for each objective
- **Quick editing**: Easily modify or delete objectives

### 📊 Progress Charts (ProgressChartScreen)
- **Data visualization**: Elegant charts powered by Vico Charts
- **Detailed statistics**: Completion percentages and trends
- **Temporal analysis**: View your progress over time
- **Material3 design**: Modern and animated charts

### ⚙️ Settings (SettingsScreen)
- **Language selection**: Spanish and English available
- **OLED theme**: Light and dark modes (OLED-optimized)
- **Persistent preferences**: Configuration saved with DataStore
- **Responsive interface**: Adaptable to different screen sizes

## 🛠️ Technology Stack

### Framework and Language

- **Kotlin 2.0.21**: Modern and safe programming language
- **Jetpack Compose**: Declarative UI with Compose BOM 2024.12.01
- **Material Design 3**: Material3 1.4.0-alpha03 with Adaptive and Material Icons

### Architecture

- **MVVM** (Model-View-ViewModel) with Clean Architecture
- **Hilt 2.46.1**: Dagger dependency injection
- **Room 2.6.0**: Local data persistence with Flow
- **Kotlin Coroutines**: Asynchronous operations
- **DataStore 1.1.1**: Preferences storage

### Core Libraries

- **Navigation Compose 2.8.5**: Screen navigation
- **Vico Charts 1.13.1**: Elegant and animated charts
- **Coil 2.7.0**: Image and GIF loading
- **Lifecycle ViewModel 2.8.7**: Lifecycle management
- **AppCompat 1.7.0**: Localization support

### Quality Tools

- **ktlint 12.1.2**: Code style analysis
- **Detekt 1.23.7**: Static analysis
- **Dependency Updates 0.51.0**: Dependency management
- **MockK 1.14.4**: Testing framework

## 📦 Project Structure

```kotlin
HabitHub/
├── app/src/main/java/com/dennnisver4/habithub/
│   ├── data/                          # Data layer
│   │   ├── DailyNote.kt              # Entity: Daily notes
│   │   ├── MonthlyObjective.kt       # Entity: Monthly objectives
│   │   ├── ObjectiveCompletion.kt    # Entity: Objective completion
│   │   ├── HabitHubDao.kt           # DAO: Database operations
│   │   ├── HabitHubDatabase.kt      # Room Database
│   │   ├── ThemePreferences.kt       # DataStore: Preferences
│   │   └── repository/               # Repositories
│   │       ├── MonthlyCalendarRepository.kt
│   │       ├── ObjectivesRepository.kt
│   │       └── ProgressRepository.kt
│   ├── di/                           # Dependency injection
│   │   └── AppModule.kt             # Hilt module
│   ├── ui/                           # Presentation layer
│   │   ├── screens/                  # Compose screens
│   │   │   ├── MonthlyCalendarScreen.kt
│   │   │   ├── ObjectivesTableScreen.kt
│   │   │   ├── ProgressChartScreen.kt
│   │   │   └── SettingsScreen.kt
│   │   ├── viewmodel/               # ViewModels
│   │   │   ├── MonthlyCalendarViewModel.kt
│   │   │   ├── ObjectivesViewModel.kt
│   │   │   └── ProgressViewModel.kt
│   │   ├── navigation/              # Navigation
│   │   │   └── Screen.kt
│   │   └── theme/                   # Material3 theme
│   │       ├── Color.kt
│   │       ├── Shape.kt
│   │       ├── Theme.kt
│   │       └── Type.kt
│   ├── HabitHubApplication.kt       # @HiltAndroidApp
│   ├── MainActivity.kt              # @AndroidEntryPoint
│   └── SplashActivity.kt            # Splash screen
├── app/src/main/res/
│   ├── values/                      # Spanish resources (default)
│   │   ├── strings.xml
│   │   ├── colors.xml
│   │   └── themes.xml
│   └── values-en/                   # English resources
│       └── strings.xml
├── .github/workflows/               # CI/CD
│   ├── android-ci.yml              # Main pipeline
│   ├── release.yml                 # Automated releases
│   ├── code-quality.yml            # Quality analysis
│   └── dependabot-auto-merge.yml   # Auto-merge
├── config/detekt/                   # Detekt configuration
│   ├── detekt.yml
│   └── baseline.xml
├── .editorconfig                    # Editor configuration
├── CONTRIBUTING.md                  # Contribution guide
├── LICENSE                          # MIT License
└── README.md                        # This file
```

## 🚀 Requirements and Setup

### System Requirements

- **Android Studio**: Ladybug 2024.2.1 or higher
- **JDK**: 11 (local) / 17 (CI/CD)
- **Android SDK**: 36
- **Gradle**: 8.13.0
- **AGP**: 8.13.0

### Project Configuration

- **Package**: `com.dennnisver4.habithub`
- **minSdk**: 26 (Android 8.0 Oreo)
- **targetSdk**: 36 (Android 14+)
- **compileSdk**: 36
- **versionCode**: 1
- **versionName**: "1.0"

### Installation Steps

1. **Clone the repository**:

   ```bash
   git clone https://github.com/mad0021/habithub.git
   cd habithub
   ```

2. **Open in Android Studio**:
   - `File` > `Open` > Select the project folder
   - Wait for Gradle to sync automatically

3. **Build the project**:

   ```bash
   ./gradlew assembleDebug
   ```

   Or from Android Studio: `Build` > `Make Project` (`Ctrl + F9`)

4. **Run the application**:
   - Connect an Android device (API 26+) or start an emulator
   - `Run` > `Run 'app'` (`Shift + F10`)

## 💾 Database

HabitHub uses Room Database for persistent local storage.

### Database Schema

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

### Repositories

- **MonthlyCalendarRepository**: Daily notes management for calendar
- **ObjectivesRepository**: CRUD operations for monthly objectives
- **ProgressRepository**: Statistics and data for charts

## 🌐 Localization

The application supports multiple languages:

- 🇪🇸 **Spanish** (default)
- 🇬🇧 **English**

Language is automatically selected based on system configuration. Users can manually change it in the Settings screen.

### Localization Files

- `res/values/strings.xml` - Spanish (60 strings)
- `res/values-en/strings.xml` - English (60 strings)
- `res/xml/locales_config.xml` - Locale configuration

## 🎨 Themes

### OLED Dark Theme

HabitHub includes a dark theme optimized for OLED displays:

- **Light Mode**: Vibrant colors with white background
- **OLED Dark Mode**: Pure black (#000000) for battery savings

Theme preferences are saved with DataStore and persist between sessions.

## 🐛 Troubleshooting

### Gradle Build Error

```bash
# Clean and rebuild
./gradlew clean
./gradlew build --refresh-dependencies
```

### Hilt Error

Verify that annotations are correct:

- `@HiltAndroidApp` in `HabitHubApplication`
- `@AndroidEntryPoint` in `MainActivity` and `SplashActivity`
- `@HiltViewModel` in all ViewModels
- `@Inject` in repository constructors

### kapt Error

```bash
# Invalidate caches in Android Studio
File > Invalidate Caches > Invalidate and Restart
```

### Charts Not Displaying

- Verify that you have data in the selected date range
- Check that Vico Charts is correctly imported in `libs.versions.toml`

## 🔄 CI/CD and Workflows

### GitHub Actions

The project includes professional automated workflows:

#### 🔨 CI Pipeline (`android-ci.yml`)
Runs on every push and pull request:
- ✅ Project build
- ✅ Unit and integration tests
- ✅ Code analysis with ktlint
- ✅ Android Lint
- ✅ Instrumented tests (on PRs)
- ✅ Test coverage report
- ✅ Debug APK generation

#### 📦 Release Pipeline (`release.yml`)
Triggered by tags `v*.*.*`:
- 📦 Release APK build
- 🔐 APK signing (if configured)
- 📝 Automatic changelog generation
- 🚀 GitHub Release creation
- 📤 Artifact upload

#### 🔍 Code Quality (`code-quality.yml`)
Runs on pull requests:
- 🔎 Static analysis with Detekt
- 📊 Code complexity analysis
- ☁️ SonarCloud integration (optional)
- 📈 Dependency updates verification

#### 🤖 Dependabot
- 📦 Automatic Gradle updates
- 🔄 GitHub Actions updates
- ✅ Auto-merge of safe patches

### Code Quality

```bash
# Check code style
./gradlew ktlintCheck

# Auto-format code
./gradlew ktlintFormat

# Static analysis
./gradlew detekt

# Android Lint
./gradlew lint

# Check dependency updates
./gradlew dependencyUpdates
```

### Creating a Release

```bash
# 1. Ensure you are on main and up to date
git checkout main
git pull origin main

# 2. Create and push a tag
git tag -a v1.0.0 -m "Release v1.0.0: Change description"
git push origin v1.0.0

# 3. GitHub Actions automatically:
#    - Builds the release APK
#    - Creates the GitHub Release
#    - Generates the changelog
#    - Uploads the APK as artifact
```

## 🧪 Testing

### Running Tests Locally

```bash
# Unit tests
./gradlew test

# Instrumented tests (requires emulator or device)
./gradlew connectedAndroidTest

# All checks (tests + lint)
./gradlew check

# With coverage report
./gradlew testDebugUnitTest jacocoTestReport
```

### Test Coverage

Reports are generated in:
- Unit tests: `app/build/reports/tests/testDebugUnitTest/index.html`
- Android Lint: `app/build/reports/lint-results.html`
- Detekt: `build/reports/detekt/detekt.html`
- Coverage: `app/build/reports/jacoco/html/index.html`

## 🤝 Contributing

Contributions are welcome! See [CONTRIBUTING.md](CONTRIBUTING.md) for more details.

### Contribution Process

1. **Fork** the repository
2. **Create** a branch: `git checkout -b feature/AmazingFeature`
3. **Commit** using Conventional Commits: `git commit -m 'feat: Add AmazingFeature'`
4. **Push**: `git push origin feature/AmazingFeature`
5. **Open** a Pull Request

### Conventional Commits

We follow [Conventional Commits](https://www.conventionalcommits.org/):

- `feat:` New feature
- `fix:` Bug fix
- `docs:` Documentation changes
- `style:` Formatting, whitespace (no code changes)
- `refactor:` Code refactoring
- `test:` Add or modify tests
- `chore:` Maintenance, dependencies
- `ci:` CI/CD changes

**Example:**
```
feat(calendar): add swipe gesture to navigate months

- Added swipe left/right to change months
- Improved animation transitions
- Updated tests

Closes #123
```

## 📊 Project Status

| Feature | Status | Details |
|---------|--------|---------|
| MVVM Architecture | ✅ **Complete** | 3 ViewModels with @HiltViewModel |
| Hilt DI | ✅ **Complete** | Dependency injection configured |
| Room Database | ✅ **Complete** | 3 entities, 1 DAO, 3 repositories |
| Localization (ES/EN) | ✅ **Complete** | 60 strings in each language |
| OLED Dark Theme | ✅ **Complete** | OLED-optimized dark theme |
| Material Design 3 | ✅ **Complete** | Material3 1.4.0-alpha03 |
| CI/CD Pipeline | ✅ **Complete** | 4 GitHub Actions workflows |
| Code Quality Tools | ✅ **Complete** | ktlint, Detekt, Dependency Updates |
| Splash Screen | ✅ **Complete** | SplashActivity with branding |
| Navigation | ✅ **Complete** | Navigation Compose with 4 screens |
| Unit Tests | 🚧 **Pending** | MockK configured, tests to implement |
| Widget Home Screen | 🔜 **Future** | Planned |
| Cloud Sync | 🔜 **Future** | Planned |
| Notifications | 🔜 **Future** | Planned |

## 🎯 Roadmap

### v1.1.0 (Next)

- [ ] Unit tests for ViewModels (70%+ coverage)
- [ ] Integration tests for Repositories
- [ ] UI tests for critical screens
- [ ] Complete architecture documentation

### v1.2.0 (Future)

- [ ] Home widget with daily objectives
- [ ] Reminder notifications
- [ ] Export/import data (JSON)
- [ ] Consecutive days streak

### v2.0.0 (Long-term)

- [ ] Cloud synchronization (Firebase)
- [ ] Objective categories
- [ ] Advanced statistics
- [ ] Tablet mode with adaptive design
- [ ] Wear OS companion app

## 📄 License

This project is licensed under the [MIT License](LICENSE). This means you can:

- ✅ Use the code freely in personal and commercial projects
- ✅ Modify and adapt the code to your needs
- ✅ Distribute the original or modified code
- ✅ Use the code in private applications

The only condition is to maintain the copyright notice and license in copies of the software.

## 👨‍💻 Developer

**Dennis Ver**

- GitHub: [@dennnisver4](https://github.com/dennnisver4)
- Email: [dennnisver4@gmail.com](mailto:dennnisver4@gmail.com)

Developed with ❤️ using Android Studio and the latest Android technologies.

## 🙏 Acknowledgements

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern declarative UI
- [Hilt](https://dagger.dev/hilt/) - Dependency injection
- [Room](https://developer.android.com/training/data-storage/room) - Local database
- [Vico Charts](https://github.com/patrykandpatrick/vico) - Elegant charts
- [Material Design 3](https://m3.material.io/) - Design system
- [GitHub Actions](https://github.com/features/actions) - Automated CI/CD

---

<div align="center">

**⭐ If you like HabitHub, give it a star ⭐**

**Start building better habits today! 💪🎯**

Made with ❤️ and ☕

</div>
