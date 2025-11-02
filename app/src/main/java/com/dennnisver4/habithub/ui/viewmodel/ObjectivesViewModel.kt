package com.dennnisver4.habithub.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennnisver4.habithub.data.MonthlyObjective
import com.dennnisver4.habithub.data.ObjectiveCompletion
import com.dennnisver4.habithub.data.repository.ObjectivesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * ViewModel para la pantalla de objetivos (ObjectivesTableScreen).
 * Maneja el estado y lógica de negocio relacionada con objetivos y completaciones diarias.
 */
@HiltViewModel
class ObjectivesViewModel @Inject constructor(
    private val repository: ObjectivesRepository
) : ViewModel() {
    
    // Estado del mes actual
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
     * Completaciones del día de hoy
     */
    val todayCompletions: StateFlow<List<ObjectiveCompletion>> = flow {
        emit(LocalDate.now().toString())
    }.flatMapLatest { today ->
        repository.getDailyCompletions(today)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    
    /**
     * Añade un nuevo objetivo al mes actual
     */
    fun addObjective(name: String) {
        if (name.isBlank()) return
        
        viewModelScope.launch {
            repository.addObjective(name, yearMonthString.value)
        }
    }
    
    /**
     * Actualiza un objetivo existente
     */
    fun updateObjective(objective: MonthlyObjective) {
        viewModelScope.launch {
            repository.updateObjective(objective)
        }
    }
    
    /**
     * Elimina un objetivo
     */
    fun deleteObjective(objective: MonthlyObjective) {
        viewModelScope.launch {
            repository.deleteObjective(objective)
        }
    }
    
    /**
     * Alterna el estado de completación de un objetivo en una fecha específica
     */
    fun toggleCompletion(objectiveId: Long, date: LocalDate) {
        viewModelScope.launch {
            repository.toggleCompletion(objectiveId, date.toString())
        }
    }
    
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
