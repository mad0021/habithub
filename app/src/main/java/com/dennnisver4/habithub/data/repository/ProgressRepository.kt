package com.dennnisver4.habithub.data.repository

import com.dennnisver4.habithub.data.DailyCompletionCount
import com.dennnisver4.habithub.data.HabitHubDao
import com.dennnisver4.habithub.data.MonthlyObjective
import com.dennnisver4.habithub.data.ObjectiveCompletion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository que proporciona datos estadísticos para gráficas de progreso.
 * Abstrae el acceso a datos de completaciones y conteos agregados.
 */
@Singleton
class ProgressRepository @Inject constructor(
    private val dao: HabitHubDao
) {
    
    /**
     * Obtiene los objetivos del mes especificado (formato yyyy-MM)
     */
    fun getMonthlyObjectives(yearMonth: String): Flow<List<MonthlyObjective>> {
        return dao.getMonthlyObjectives(yearMonth)
    }
    
    /**
     * Obtiene el conteo diario de completaciones para el mes especificado.
     * Retorna una lista de DailyCompletionCount con la fecha y cantidad de objetivos completados por día.
     */
    fun getMonthlyCompletionCounts(yearMonth: String): Flow<List<DailyCompletionCount>> {
        // Primero obtenemos los objetivos del mes
        return getMonthlyObjectives(yearMonth).flatMapLatest { objectives ->
            val objectiveIds = objectives.map { it.id }
            if (objectiveIds.isNotEmpty()) {
                dao.getMonthlyCompletionCounts(objectiveIds, yearMonth)
            } else {
                flowOf(emptyList())
            }
        }
    }
    
    /**
     * Obtiene todas las completaciones del mes para análisis detallado
     */
    fun getMonthlyCompletions(yearMonth: String): Flow<List<ObjectiveCompletion>> {
        return getMonthlyObjectives(yearMonth).flatMapLatest { objectives ->
            val objectiveIds = objectives.map { it.id }
            if (objectiveIds.isNotEmpty()) {
                dao.getMonthlyCompletions(objectiveIds, yearMonth)
            } else {
                flowOf(emptyList())
            }
        }
    }
}
