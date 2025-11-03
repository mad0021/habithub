# HabitHub - Contributing Guide

Thank you for your interest in contributing to HabitHub! 🎉

## 📋 Table of Contents
- [Code of Conduct](#code-of-conduct)
- [How Can I Contribute?](#how-can-i-contribute)
- [Style Guide](#style-guide)
- [Pull Request Process](#pull-request-process)
- [Project Structure](#project-structure)

## 📜 Code of Conduct

This project adheres to a code of conduct. By participating, you are expected to uphold this code. Please report unacceptable behavior.

## 🤝 How Can I Contribute?

### 🐛 Reporting Bugs

Bugs are tracked as [GitHub issues](https://github.com/dennnisver4/HabitHub/issues). Create an issue and provide the following information:

- Clear and descriptive title
- Detailed steps to reproduce the problem
- Expected vs actual behavior
- Screenshots if possible
- Device information (model, Android version)

### ✨ Suggesting Enhancements

Enhancement suggestions are also tracked as issues. Include:

- Clear description of the functionality
- Problem it solves
- Usage examples or mockups

### 💻 Pull Requests

1. **Fork** the repository
2. **Create** a branch for your feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'feat: Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

## 🎨 Style Guide

### Kotlin

- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use 4 spaces for indentation
- Maximum 120 characters per line
- Use descriptive English names for variables and functions

### Commits

We follow [Conventional Commits](https://www.conventionalcommits.org/):

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Types:**
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Formatting, whitespace, etc.
- `refactor`: Code refactoring
- `test`: Add or modify tests
- `chore`: Maintenance, dependencies

**Examples:**
```
feat(calendar): add swipe to delete notes
fix(objectives): resolve crash when adding empty objective
docs(readme): update installation instructions
```

### Architecture

- **MVVM** with Clean Architecture
- **Hilt** for dependency injection
- **Room** for persistence
- **Jetpack Compose** for UI
- **Coroutines** for asynchronous operations

### Tests

- Unit tests for ViewModels
- Integration tests for Repositories
- UI tests for critical flows
- Minimum coverage: 70%

## 🔄 Pull Request Process

1. **Update** your fork with the main branch
2. **Ensure** that tests pass
3. **Run** lint and fix issues
4. **Update** documentation if necessary
5. **Complete** the PR template
6. **Wait** for review

### PR Checklist

- [ ] Tests pass locally
- [ ] Code follows project style
- [ ] Documentation updated
- [ ] Works in Spanish and English
- [ ] Tested in light and dark modes
- [ ] No warnings or memory leaks

## 📁 Project Structure

```
app/src/main/java/com/dennnisver4/habithub/
├── data/                       # Data layer
│   ├── *.kt                    # Room entities
│   └── repository/             # Repositories
├── di/                         # Dependency injection
├── ui/
│   ├── navigation/             # Navigation
│   ├── screens/                # Compose screens
│   ├── theme/                  # Theme and styles
│   └── viewmodel/              # ViewModels
├── MainActivity.kt
└── HabitHubApplication.kt
```

## 🧪 Local Testing

```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest

# Lint
./gradlew lint

# Build
./gradlew assembleDebug
```

## 📞 Contact

Questions? Open an [issue](https://github.com/dennnisver4/HabitHub/issues) or contact [@dennnisver4](https://github.com/dennnisver4).

## 📄 License

By contributing to HabitHub, you agree that your contributions will be licensed under the [MIT License](LICENSE). This means your code will be open source and can be freely used by others, always maintaining credit to the original authors.

---

Thank you for contributing to HabitHub! 🚀
