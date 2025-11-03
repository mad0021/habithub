# ============================================
# HABITHUB - REGLAS DE SEGURIDAD PROGUARD
# Configuración de máxima seguridad y privacidad
# ============================================

# Ofuscación agresiva para proteger código
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Optimizaciones de seguridad
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-allowaccessmodification
-repackageclasses ''

# Remover logs en producción para no filtrar información
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}

# Ocultar información del código fuente
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# Mantener clases necesarias para el funcionamiento
-keep class com.dennnisver4.habithub.HabitHubApplication { *; }
-keep class com.dennnisver4.habithub.MainActivity { *; }
-keep class com.dennnisver4.habithub.SplashActivity { *; }

# Room Database - mantener entidades y DAOs
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Hilt/Dagger
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.lifecycle.HiltViewModel
-keep @dagger.hilt.android.lifecycle.HiltViewModel class * { *; }

# Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Kotlin
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# Serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

# DataStore
-keep class androidx.datastore.*.** { *; }

# Remover código de analytics y tracking (si existiera)
-assumenosideeffects class * implements java.util.logging.Logger {
    <methods>;
}
