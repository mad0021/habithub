# HabitHub - Aplicación de Seguimiento de Hábitos

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

## 🔮 Futuras Mejoras

- [ ] Recordatorios y notificaciones
- [ ] Widgets para la pantalla de inicio
- [ ] Racha de días consecutivos
- [ ] Exportar/importar datos
- [ ] Temas personalizados (modo oscuro/claro)
- [ ] Categorías de hábitos
- [ ] Objetivos semanales/mensuales
- [ ] Sincronización en la nube

## 📄 Licencia

Este proyecto es de uso personal.

## 👨‍💻 Desarrollador

Desarrollado para uso personal con Android Studio Narwhal 4 Feature Drop.

---

**¡Empieza hoy mismo a construir mejores hábitos con HabitHub! 💪🎯**
