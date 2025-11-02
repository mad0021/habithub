package com.dennnisver4.habithub.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennnisver4.habithub.data.DailyCompletionCount
import com.dennnisver4.habithub.data.MonthlyObjective
import com.dennnisver4.habithub.data.repository.ProgressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * ViewModel para la pantalla de gráficas de progreso (ProgressChartScreen).
 * Maneja el estado y datos estadísticos de completaciones mensuales.
 */
@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val repository: ProgressRepository
) : ViewModel() {
    
    // Estado del mes actual para las estadísticas
    private val _currentYearMonth = MutableStateFlow(YearMonth.now())
    val currentYearMonth: StateFlow<YearMonth> = _currentYearMonth.asStateFlow()
    
    // String del mes en formato yyyy-MM para queries a la base de datos
    private val yearMonthString: StateFlow<String> = currentYearMonth.map { 
        it.format(DateTimeFormatter.ofPattern("yyyy-MM"))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")
    
    /**
     * Lista de objetivos del mes actual
     */
    val monthlyObjectives: StateFlow<List<MonthlyObjective>> = yearMonthString.flatMapLatest { yearMonth ->
        if (yearMonth.isNotEmpty()) {
            repository.getMonthlyObjectives(yearMonth)
        } else {
            flowOf(emptyList())
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    
    /**
     * Conteo diario de completaciones para el mes actual.
     * Cada elemento contiene la fecha y el número de objetivos completados ese día.
     */
    val monthlyCompletionCounts: StateFlow<List<DailyCompletionCount>> = yearMonthString.flatMapLatest { yearMonth ->
        if (yearMonth.isNotEmpty()) {
            repository.getMonthlyCompletionCounts(yearMonth)
        } else {
            flowOf(emptyList())
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    
    /**
     * Navega al mes anterior
     */
    fun navigateToPreviousMonth() {
        _currentYearMonth.value = _currentYearMonth.value.minusMonths(1)
    }
    
    /**
     * Navega al mes siguiente
     */
    fun navigateToNextMonth() {
        _currentYearMonth.value = _currentYearMonth.value.plusMonths(1)
    }
    
    /**
     * Navega a un mes específico
     */
    fun navigateToMonth(yearMonth: YearMonth) {
        _currentYearMonth.value = yearMonth
    }
}
