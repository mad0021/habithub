package com.dennnisver4.habithub.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.dennnisver4.habithub.R
import com.dennnisver4.habithub.ui.viewmodel.MonthlyCalendarViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthlyCalendarScreen(
    viewModel: MonthlyCalendarViewModel = hiltViewModel(),
    onNavigateToObjectives: () -> Unit,
    onNavigateToChart: () -> Unit,
    onNavigateToSettings: () -> Unit,
) {
    val currentYearMonth by viewModel.currentYearMonth.collectAsState()
    val monthlyNotes by viewModel.monthlyNotes.collectAsState()

    val configuration = LocalConfiguration.current
    val locale = ConfigurationCompat.getLocales(configuration)[0] ?: Locale.getDefault()

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showNoteDialog by remember { mutableStateOf(false) }

    val notesMap =
        remember(monthlyNotes) {
            monthlyNotes.associateBy { it.date }
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text =
                            currentYearMonth.format(
                                DateTimeFormatter.ofPattern("MMMM yyyy", locale),
                            ).uppercase(),
                        style = MaterialTheme.typography.headlineSmall,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateToPreviousMonth() }) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = stringResource(R.string.previous_month))
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.navigateToNextMonth() }) {
                        Icon(Icons.Default.ChevronRight, contentDescription = stringResource(R.string.next_month))
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = stringResource(R.string.settings_icon))
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.CalendarMonth, null) },
                    label = { Text(stringResource(R.string.calendar_title)) },
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToObjectives,
                    icon = { Icon(Icons.Default.ChecklistRtl, null) },
                    label = { Text(stringResource(R.string.objectives_title)) },
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToChart,
                    icon = { Icon(Icons.Default.ShowChart, null) },
                    label = { Text(stringResource(R.string.chart_title)) },
                )
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // Días del mes
            val daysInMonth = currentYearMonth.lengthOfMonth()
            items((1..daysInMonth).toList()) { day ->
                val date = LocalDate.of(currentYearMonth.year, currentYearMonth.month, day)
                val note = notesMap[date.toString()]

                DayCard(
                    date = date,
                    note = note?.note,
                    onClick = {
                        selectedDate = date
                        showNoteDialog = true
                    },
                )
            }
        }
    }

    if (showNoteDialog) {
        NoteDialog(
            date = selectedDate,
            currentNote = notesMap[selectedDate.toString()]?.note ?: "",
            onDismiss = { showNoteDialog = false },
            onSave = { note ->
                viewModel.saveDailyNote(selectedDate, note)
                showNoteDialog = false
            },
            onDelete = {
                viewModel.deleteDailyNote(selectedDate)
                showNoteDialog = false
            },
        )
    }
}

@Composable
fun DayCard(
    date: LocalDate,
    note: String?,
    onClick: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val locale = ConfigurationCompat.getLocales(configuration)[0] ?: Locale.getDefault()
    val isToday = date == LocalDate.now()
    val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, locale)

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.large,
        colors =
            CardDefaults.cardColors(
                containerColor =
                    if (isToday) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    },
            ),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Fecha
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = date.dayOfMonth.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }

                Column {
                    Text(
                        text = dayOfWeek.uppercase(),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    if (!note.isNullOrBlank()) {
                        Text(
                            text = note,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 2,
                            modifier = Modifier.padding(top = 4.dp),
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.no_note),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                            modifier = Modifier.padding(top = 4.dp),
                        )
                    }
                }
            }

            Icon(
                imageVector = if (note.isNullOrBlank()) Icons.Default.Add else Icons.Default.Edit,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
fun NoteDialog(
    date: LocalDate,
    currentNote: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit,
    onDelete: () -> Unit,
) {
    var noteText by remember { mutableStateOf(currentNote) }
    val configuration = LocalConfiguration.current
    val locale = ConfigurationCompat.getLocales(configuration)[0] ?: Locale.getDefault()
    val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, locale)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column {
                Text(
                    text = "${date.dayOfMonth} ${stringResource(
                        R.string.date_separator,
                    )} ${date.month.getDisplayName(TextStyle.FULL, locale)}",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = dayOfWeek.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        },
        text = {
            OutlinedTextField(
                value = noteText,
                onValueChange = { noteText = it },
                label = { Text(stringResource(R.string.note_label)) },
                placeholder = { Text(stringResource(R.string.note_hint)) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5,
                shape = MaterialTheme.shapes.medium,
            )
        },
        confirmButton = {
            FilledTonalButton(
                onClick = { onSave(noteText) },
                shape = MaterialTheme.shapes.medium,
            ) {
                Text(stringResource(R.string.save))
            }
        },
        dismissButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (currentNote.isNotBlank()) {
                    TextButton(onClick = onDelete) {
                        Text(stringResource(R.string.delete))
                    }
                }
                TextButton(onClick = onDismiss) {
                    Text(stringResource(R.string.cancel))
                }
            }
        },
        shape = MaterialTheme.shapes.extraLarge,
    )
}
