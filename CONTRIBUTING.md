# HabitHub - Contributing Guide

¡Gracias por tu interés en contribuir a HabitHub! 🎉

## 📋 Tabla de Contenidos
- [Código de Conducta](#código-de-conducta)
- [¿Cómo puedo contribuir?](#cómo-puedo-contribuir)
- [Guía de Estilo](#guía-de-estilo)
- [Proceso de Pull Request](#proceso-de-pull-request)
- [Estructura del Proyecto](#estructura-del-proyecto)

## 📜 Código de Conducta

Este proyecto se adhiere a un código de conducta. Al participar, se espera que mantengas este código. Por favor, reporta comportamientos inaceptables.

## 🤝 ¿Cómo puedo contribuir?

### 🐛 Reportar Bugs

Los bugs se rastrean como [GitHub issues](https://github.com/dennnisver4/HabitHub/issues). Crea un issue y proporciona la siguiente información:

- Título claro y descriptivo
- Pasos detallados para reproducir el problema
- Comportamiento esperado vs actual
- Screenshots si es posible
- Información del dispositivo (modelo, versión Android)

### ✨ Sugerir Mejoras

Las sugerencias de mejoras también se rastrean como issues. Incluye:

- Descripción clara de la funcionalidad
- Problema que resuelve
- Ejemplos de uso o mockups

### 💻 Pull Requests

1. **Fork** el repositorio
2. **Crea** una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** tus cambios (`git commit -m 'feat: Add some AmazingFeature'`)
4. **Push** a la rama (`git push origin feature/AmazingFeature`)
5. **Abre** un Pull Request

## 🎨 Guía de Estilo

### Kotlin

- Seguir [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Usar 4 espacios para indentación
- Máximo 120 caracteres por línea
- Usar nombres descriptivos en inglés para variables y funciones

### Commits

Seguimos [Conventional Commits](https://www.conventionalcommits.org/):

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Tipos:**
- `feat`: Nueva funcionalidad
- `fix`: Corrección de bug
- `docs`: Cambios en documentación
- `style`: Formateo, espacios, etc.
- `refactor`: Refactorización de código
- `test`: Añadir o modificar tests
- `chore`: Mantenimiento, dependencias

**Ejemplos:**
```
feat(calendar): add swipe to delete notes
fix(objectives): resolve crash when adding empty objective
docs(readme): update installation instructions
```

### Arquitectura

- **MVVM** con Clean Architecture
- **Hilt** para inyección de dependencias
- **Room** para persistencia
- **Jetpack Compose** para UI
- **Coroutines** para asincronía

### Tests

- Tests unitarios para ViewModels
- Tests de integración para Repositories
- UI tests para flujos críticos
- Cobertura mínima: 70%

## 🔄 Proceso de Pull Request

1. **Actualiza** tu fork con la rama main
2. **Asegúrate** de que los tests pasen
3. **Ejecuta** lint y corrígelo
4. **Actualiza** la documentación si es necesario
5. **Completa** el template del PR
6. **Espera** la revisión

### Checklist del PR

- [ ] Tests pasan localmente
- [ ] Código sigue el estilo del proyecto
- [ ] Documentación actualizada
- [ ] Funciona en español e inglés
- [ ] Probado en modo claro y oscuro
- [ ] Sin warnings ni memory leaks

## 📁 Estructura del Proyecto

```
app/src/main/java/com/dennnisver4/habithub/
├── data/                       # Capa de datos
│   ├── *.kt                    # Entidades de Room
│   └── repository/             # Repositorios
├── di/                         # Inyección de dependencias
├── ui/
│   ├── navigation/             # Navegación
│   ├── screens/                # Pantallas Compose
│   ├── theme/                  # Tema y estilos
│   └── viewmodel/              # ViewModels
├── MainActivity.kt
└── HabitHubApplication.kt
```

## 🧪 Testing Local

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

## 📞 Contacto

¿Preguntas? Abre un [issue](https://github.com/dennnisver4/HabitHub/issues) o contacta a [@dennnisver4](https://github.com/dennnisver4).

## 📄 Licencia

Al contribuir a HabitHub, aceptas que tus contribuciones serán licenciadas bajo la [Licencia MIT](LICENSE). Esto significa que tu código será de código abierto y podrá ser utilizado libremente por otros, manteniendo siempre el crédito a los autores originales.

---

¡Gracias por contribuir a HabitHub! 🚀
