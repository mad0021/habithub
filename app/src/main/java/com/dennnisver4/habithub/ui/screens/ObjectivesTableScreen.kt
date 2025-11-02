package com.dennnisver4.habithub.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.core.os.ConfigurationCompat
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dennnisver4.habithub.R
import com.dennnisver4.habithub.ui.viewmodel.ObjectivesViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectivesTableScreen(
    onNavigateToCalendar: () -> Unit,
    onNavigateToChart: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: ObjectivesViewModel = hiltViewModel()
) {
    val objectives by viewModel.monthlyObjectives.collectAsState()
    val today = LocalDate.now()
    val todayCompletions by viewModel.todayCompletions.collectAsState()
    
    val configuration = LocalConfiguration.current
    val locale = ConfigurationCompat.getLocales(configuration)[0] ?: Locale.getDefault()
    
    var showAddDialog by remember { mutableStateOf(false) }
    var objectiveToDelete by remember { mutableStateOf<Long?>(null) }
    
    val completedObjectiveIds = remember(todayCompletions) {
        todayCompletions.filter { it.isCompleted }.map { it.objectiveId }.toSet()
    }
    
    val dateSeparator = stringResource(R.string.date_separator)
    val dayOfWeek = today.dayOfWeek.getDisplayName(TextStyle.FULL, locale)
    val todayFormatted = "${today.dayOfMonth} $dateSeparator ${today.month.getDisplayName(TextStyle.FULL, locale)}"
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = stringResource(R.string.objectives_title),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = todayFormatted,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = stringResource(R.string.settings_icon))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showAddDialog = true },
                icon = { Icon(Icons.Default.Add, null) },
                text = { Text(stringResource(R.string.add_objective)) },
                shape = MaterialTheme.shapes.large
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToCalendar,
                    icon = { Icon(Icons.Default.CalendarMonth, null) },
                    label = { Text(stringResource(R.string.calendar_title)) }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.ChecklistRtl, null) },
                    label = { Text(stringResource(R.string.objectives_title)) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToChart,
                    icon = { Icon(Icons.Default.ShowChart, null) },
                    label = { Text(stringResource(R.string.chart_title)) }
                )
            }
        }
    ) { paddingValues ->
        if (objectives.isEmpty()) {
            // Estado vacío
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ChecklistRtl,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                    )
                    Text(
                        text = stringResource(R.string.no_objectives),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(R.string.no_objectives_hint),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            // Lista de objetivos del día
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Resumen del día
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = dayOfWeek.uppercase(),
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "${completedObjectiveIds.size}/${objectives.size}",
                                    style = MaterialTheme.typography.displaySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = stringResource(R.string.objectives_completed),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            
                            val progress = if (objectives.isNotEmpty()) {
                                completedObjectiveIds.size.toFloat() / objectives.size
                            } else 0f
                            
                            CircularProgressIndicator(
                                progress = { progress },
                                modifier = Modifier.size(80.dp),
                                strokeWidth = 8.dp,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        }
                    }
                }
                
                // Lista de objetivos
                items(objectives) { objective ->
                    val isCompleted = completedObjectiveIds.contains(objective.id)
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = if (isCompleted) 
                                MaterialTheme.colorScheme.secondaryContainer 
                            else 
                                MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Checkbox(
                                    checked = isCompleted,
                                    onCheckedChange = {
                                        viewModel.toggleCompletion(objective.id, today)
                                    }
                                )
                                Text(
                                    text = objective.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            
                            IconButton(
                                onClick = { objectiveToDelete = objective.id }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = stringResource(R.string.delete_icon),
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    
    if (showAddDialog) {
        AddObjectiveDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { name ->
                viewModel.addObjective(name)
                showAddDialog = false
            }
        )
    }
    
    objectiveToDelete?.let { objectiveId ->
        val objective = objectives.find { it.id == objectiveId }
        objective?.let {
            AlertDialog(
                onDismissRequest = { objectiveToDelete = null },
                title = { Text(stringResource(R.string.delete_objective_title)) },
                text = { Text(stringResource(R.string.delete_objective_message, it.name)) },
                confirmButton = {
                    FilledTonalButton(
                        onClick = {
                            viewModel.deleteObjective(it)
                            objectiveToDelete = null
                        },
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text(stringResource(R.string.delete))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { objectiveToDelete = null }) {
                        Text(stringResource(R.string.cancel))
                    }
                },
                shape = MaterialTheme.shapes.extraLarge
            )
        }
    }
}

@Composable
fun ObjectiveRow(
    objective: com.dennnisver4.habithub.data.MonthlyObjective,
    currentYearMonth: YearMonth,
    daysInMonth: Int,
    completionsMap: Map<String, Set<Long>>,
    onToggleCompletion: (Long, LocalDate) -> Unit,
    onDelete: () -> Unit,
    isEvenRow: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isEvenRow) MaterialTheme.colorScheme.surface
                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
            )
            .padding(8.dp)
    ) {
        // Nombre del objetivo
        Row(
            modifier = Modifier
                .width(150.dp)
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = objective.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = onDelete,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.delete_icon),
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
        
        // Celdas clickeables con marcas
        LazyRow(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items((1..daysInMonth).toList()) { day ->
                val date = LocalDate.of(currentYearMonth.year, currentYearMonth.month, day)
                val dateStr = date.toString()
                val isCompleted = completionsMap[dateStr]?.contains(objective.id) == true
                
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .padding(2.dp)
                        .background(
                            color = if (isCompleted) 
                                MaterialTheme.colorScheme.primaryContainer
                            else 
                                MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.small
                        )
                        .clickable { onToggleCompletion(objective.id, date) },
                    contentAlignment = Alignment.Center
                ) {
                    if (isCompleted) {
                        Text(
                            text = "✓",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AddObjectiveDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var objectiveName by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.add_objective)) },
        text = {
            OutlinedTextField(
                value = objectiveName,
                onValueChange = { objectiveName = it },
                label = { Text(stringResource(R.string.objective_name)) },
                placeholder = { Text(stringResource(R.string.objective_hint)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )
        },
        confirmButton = {
            FilledTonalButton(
                onClick = { if (objectiveName.isNotBlank()) onConfirm(objectiveName) },
                enabled = objectiveName.isNotBlank(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(stringResource(R.string.add))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        },
        shape = MaterialTheme.shapes.extraLarge
    )
}
