package com.dennnisver4.habithub.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.dennnisver4.habithub.R
import com.dennnisver4.habithub.ui.viewmodel.ProgressViewModel
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressChartScreen(
    onNavigateToCalendar: () -> Unit,
    onNavigateToObjectives: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: ProgressViewModel = hiltViewModel(),
) {
    val currentYearMonth by viewModel.currentYearMonth.collectAsState()
    val objectives by viewModel.monthlyObjectives.collectAsState()
    val completionCounts by viewModel.monthlyCompletionCounts.collectAsState()

    val configuration = LocalConfiguration.current
    val locale = ConfigurationCompat.getLocales(configuration)[0] ?: Locale.getDefault()

    val chartData =
        remember(completionCounts, currentYearMonth) {
            val daysInMonth = currentYearMonth.lengthOfMonth()
            val countsMap =
                completionCounts.associate {
                    it.date.substring(8, 10).toInt() to it.count
                }

            (1..daysInMonth).map { day ->
                countsMap[day] ?: 0
            }
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
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    ),
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToCalendar,
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
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.ShowChart, null) },
                    label = { Text(stringResource(R.string.chart_title)) },
                )
            }
        },
    ) { paddingValues ->
        if (objectives.isEmpty()) {
            // Estado vacío
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(32.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.ShowChart,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                    )
                    Text(
                        text = stringResource(R.string.no_data_chart),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = stringResource(R.string.no_data_chart_hint),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        } else {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                // Estadísticas
                ProgressStatsCard(
                    objectives = objectives,
                    completionCounts = completionCounts,
                )

                // Gráfica
                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    shape = MaterialTheme.shapes.extraLarge,
                ) {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.daily_progress),
                            style = MaterialTheme.typography.titleLarge,
                        )

                        // Gráfica personalizada con Canvas
                        SimplePointChart(
                            data = chartData,
                            totalObjectives = objectives.size,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SimplePointChart(
    data: List<Int>,
    totalObjectives: Int,
    modifier: Modifier = Modifier,
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val lineColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    val textColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)

    Canvas(modifier = modifier.padding(16.dp)) {
        val width = size.width
        val height = size.height
        val paddingLeft = 70f
        val paddingRight = 20f
        val paddingTop = 30f
        val paddingBottom = 50f

        val chartWidth = width - paddingLeft - paddingRight
        val chartHeight = height - paddingTop - paddingBottom

        if (data.isEmpty() || totalObjectives == 0) return@Canvas

        // Calcular posiciones
        val stepX = chartWidth / (data.size - 1).coerceAtLeast(1)
        val stepY = chartHeight / totalObjectives.coerceAtLeast(1)

        // Dibujar líneas de cuadrícula horizontales (niveles de objetivos)
        for (i in 0..totalObjectives) {
            val y = height - paddingBottom - (i * stepY)

            // Línea de cuadrícula
            drawLine(
                color = onSurfaceColor.copy(alpha = 0.15f),
                start = Offset(paddingLeft, y),
                end = Offset(width - paddingRight, y),
                strokeWidth = 1f,
            )

            // Etiqueta del eje Y (número de objetivos)
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    i.toString(),
                    paddingLeft - 35f,
                    y + 10f,
                    android.graphics.Paint().apply {
                        color = textColor.hashCode()
                        textSize = 32f
                        textAlign = android.graphics.Paint.Align.CENTER
                        isAntiAlias = true
                    },
                )
            }
        }

        // Dibujar ejes
        // Eje Y
        drawLine(
            color = onSurfaceColor.copy(alpha = 0.6f),
            start = Offset(paddingLeft, paddingTop),
            end = Offset(paddingLeft, height - paddingBottom),
            strokeWidth = 3f,
        )

        // Eje X
        drawLine(
            color = onSurfaceColor.copy(alpha = 0.6f),
            start = Offset(paddingLeft, height - paddingBottom),
            end = Offset(width - paddingRight, height - paddingBottom),
            strokeWidth = 3f,
        )

        // Etiquetas del eje X (días del mes) - mostrar algunos días
        val daysToShow = listOf(1, 5, 10, 15, 20, 25, data.size)
        daysToShow.forEach { day ->
            if (day <= data.size) {
                val x = paddingLeft + ((day - 1) * stepX)
                val y = height - paddingBottom

                // Marca en el eje
                drawLine(
                    color = onSurfaceColor.copy(alpha = 0.6f),
                    start = Offset(x, y),
                    end = Offset(x, y + 10f),
                    strokeWidth = 2f,
                )

                // Número del día
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        day.toString(),
                        x,
                        y + 35f,
                        android.graphics.Paint().apply {
                            color = textColor.hashCode()
                            textSize = 28f
                            textAlign = android.graphics.Paint.Align.CENTER
                            isAntiAlias = true
                        },
                    )
                }
            }
        }

        // Conectar puntos con líneas y dibujar puntos
        var previousPoint: Offset? = null

        data.forEachIndexed { index, value ->
            val x = paddingLeft + (index * stepX)
            val y = height - paddingBottom - (value * stepY)
            val currentPoint = Offset(x, y)

            // Dibujar línea desde el punto anterior
            previousPoint?.let { prev ->
                drawLine(
                    color = lineColor,
                    start = prev,
                    end = currentPoint,
                    strokeWidth = 3f,
                )
            }

            // Dibujar punto
            drawCircle(
                color = primaryColor,
                radius = 10f,
                center = currentPoint,
            )

            // Círculo blanco interior para mejor visibilidad
            drawCircle(
                color = primaryColor.copy(alpha = 0.3f),
                radius = 6f,
                center = currentPoint,
            )

            previousPoint = currentPoint
        }
    }
}

@Composable
fun ProgressStatsCard(
    objectives: List<com.dennnisver4.habithub.data.MonthlyObjective>,
    completionCounts: List<com.dennnisver4.habithub.data.DailyCompletionCount>,
) {
    val totalObjectives = objectives.size
    val totalCompletions = completionCounts.sumOf { it.count }
    val averagePerDay =
        if (completionCounts.isNotEmpty()) {
            completionCounts.sumOf { it.count }.toFloat() / completionCounts.size
        } else {
            0f
        }
    val bestDay = completionCounts.maxByOrNull { it.count }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = stringResource(R.string.monthly_statistics),
                style = MaterialTheme.typography.titleLarge,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ProgressStatItem(
                    label = stringResource(R.string.total_objectives),
                    value = totalObjectives.toString(),
                )
                ProgressStatItem(
                    label = stringResource(R.string.total_completions),
                    value = totalCompletions.toString(),
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ProgressStatItem(
                    label = stringResource(R.string.average_per_day),
                    value = String.format("%.1f", averagePerDay),
                )
                ProgressStatItem(
                    label = stringResource(R.string.best_day),
                    value = bestDay?.let { "${it.count}" } ?: "0",
                )
            }
        }
    }
}

@Composable
fun ProgressStatItem(
    label: String,
    value: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
