package com.dennnisver4.habithub.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensión para crear el DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_preferences")

/**
 * Modos de tema disponibles
 */
enum class ThemeMode {
    LIGHT, // Tema claro
    DARK, // Tema oscuro OLED
    SYSTEM, // Seguir el tema del sistema
}

/**
 * Gestor de preferencias del tema usando DataStore
 */
class ThemePreferences(private val context: Context) {
    private val THEME_MODE_KEY = stringPreferencesKey("theme_mode")

    /**
     * Flow que emite el modo de tema actual
     */
    val themeMode: Flow<ThemeMode> =
        context.dataStore.data
            .map { preferences ->
                val themeName = preferences[THEME_MODE_KEY] ?: ThemeMode.SYSTEM.name
                try {
                    ThemeMode.valueOf(themeName)
                } catch (e: IllegalArgumentException) {
                    ThemeMode.SYSTEM
                }
            }

    /**
     * Guarda el modo de tema seleccionado
     */
    suspend fun setThemeMode(mode: ThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = mode.name
        }
    }
}
