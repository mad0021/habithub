package com.dennnisver4.habithub.ui.screens

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.dennnisver4.habithub.BuildConfig
import com.dennnisver4.habithub.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var showLanguageDialog by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }

    val currentLanguage =
        remember {
            val locales = AppCompatDelegate.getApplicationLocales()
            if (locales.isEmpty) {
                "system"
            } else {
                locales[0]?.language ?: "system"
            }
        }

    val themePreferences = remember { com.dennnisver4.habithub.data.ThemePreferences(context) }
    val currentTheme by themePreferences.themeMode.collectAsState(
        initial = com.dennnisver4.habithub.data.ThemeMode.SYSTEM,
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.settings_title),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    FilledTonalIconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, stringResource(R.string.back))
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
            )
        },
    ) { padding ->
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }

            // Sección de Idioma
            item {
                SectionHeader(stringResource(R.string.language_section))
            }

            item {
                SettingsCard {
                    SettingsItem(
                        icon = Icons.Default.Language,
                        title = stringResource(R.string.select_language),
                        subtitle =
                            when (currentLanguage) {
                                "es" -> stringResource(R.string.language_spanish)
                                "en" -> stringResource(R.string.language_english)
                                else -> stringResource(R.string.language_system)
                            },
                        onClick = { showLanguageDialog = true },
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Sección de Apariencia
            item {
                SectionHeader(stringResource(R.string.theme_section))
            }

            item {
                SettingsCard {
                    SettingsItem(
                        icon = Icons.Default.Palette,
                        title = stringResource(R.string.theme_mode),
                        subtitle =
                            when (currentTheme) {
                                com.dennnisver4.habithub.data.ThemeMode.LIGHT -> stringResource(R.string.theme_light)
                                com.dennnisver4.habithub.data.ThemeMode.DARK -> stringResource(R.string.theme_dark)
                                com.dennnisver4.habithub.data.ThemeMode.SYSTEM -> stringResource(R.string.theme_system)
                            },
                        onClick = { showThemeDialog = true },
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Sección de Información
            item {
                SectionHeader(stringResource(R.string.app_info_section))
            }

            item {
                SettingsCard {
                    SettingsItem(
                        icon = Icons.Default.Info,
                        title = stringResource(R.string.app_version),
                        subtitle = BuildConfig.VERSION_NAME,
                        onClick = null,
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }

    // Diálogo de selección de idioma
    if (showLanguageDialog) {
        LanguageSelectionDialog(
            currentLanguage = currentLanguage,
            onDismiss = { showLanguageDialog = false },
            onLanguageSelected = { languageCode ->
                showLanguageDialog = false
                setAppLanguage(context, languageCode)
                // AppCompatDelegate reiniciará automáticamente todas las Activities
            },
        )
    }

    // Diálogo de selección de tema
    if (showThemeDialog) {
        ThemeSelectionDialog(
            currentTheme = currentTheme,
            onDismiss = { showThemeDialog = false },
            onThemeSelected = { theme ->
                showThemeDialog = false
                scope.launch {
                    themePreferences.setThemeMode(theme)
                }
            },
        )
    }
}

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
    )
}

@Composable
fun SettingsCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            ),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
        ) {
            content()
        }
    }
}

@Composable
fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: (() -> Unit)?,
) {
    val modifier =
        if (onClick != null) {
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
        } else {
            Modifier.fillMaxWidth()
        }

    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp),
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        if (onClick != null) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
fun LanguageSelectionDialog(
    currentLanguage: String,
    onDismiss: () -> Unit,
    onLanguageSelected: (String) -> Unit,
) {
    val languages =
        listOf(
            "system" to stringResource(R.string.language_system),
            "es" to stringResource(R.string.language_spanish),
            "en" to stringResource(R.string.language_english),
        )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                stringResource(R.string.select_language),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                languages.forEach { (code, name) ->
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable { onLanguageSelected(code) }
                                .padding(vertical = 12.dp, horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = currentLanguage == code,
                            onClick = { onLanguageSelected(code) },
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        },
    )
}

@Composable
fun ThemeSelectionDialog(
    currentTheme: com.dennnisver4.habithub.data.ThemeMode,
    onDismiss: () -> Unit,
    onThemeSelected: (com.dennnisver4.habithub.data.ThemeMode) -> Unit,
) {
    val themes =
        listOf(
            com.dennnisver4.habithub.data.ThemeMode.SYSTEM to stringResource(R.string.theme_system),
            com.dennnisver4.habithub.data.ThemeMode.LIGHT to stringResource(R.string.theme_light),
            com.dennnisver4.habithub.data.ThemeMode.DARK to stringResource(R.string.theme_dark),
        )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                stringResource(R.string.theme_mode),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                themes.forEach { (mode, name) ->
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable { onThemeSelected(mode) }
                                .padding(vertical = 12.dp, horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = currentTheme == mode,
                            onClick = { onThemeSelected(mode) },
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        },
    )
}

private fun setAppLanguage(
    context: Context,
    languageCode: String,
) {
    try {
        val localeList =
            when (languageCode) {
                "system" -> LocaleListCompat.getEmptyLocaleList()
                else -> LocaleListCompat.forLanguageTags(languageCode)
            }

        if (languageCode != "system" && localeList.isEmpty) {
            return
        }

        AppCompatDelegate.setApplicationLocales(localeList)
    } catch (e: Exception) {
        // Silenciosamente ignorar errores
    }
}
