package com.dennnisver4.habithub.data.repository

import com.dennnisver4.habithub.data.HabitHubDao
import com.dennnisver4.habithub.data.MonthlyObjective
import com.dennnisver4.habithub.data.ObjectiveCompletion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository que abstrae el acceso a datos de objetivos y completaciones.
 * Proporciona una capa de abstracción entre el ViewModel y la base de datos.
 */
@Singleton
class ObjectivesRepository
    @Inject
    constructor(
        private val dao: HabitHubDao,
    ) {
        /**
         * Obtiene los objetivos del mes especificado (formato yyyy-MM)
         */
        fun getMonthlyObjectives(yearMonth: String): Flow<List<MonthlyObjective>> {
            return dao.getMonthlyObjectives(yearMonth)
        }

        /**
         * Obtiene las completaciones de un día específico
         */
        fun getDailyCompletions(date: String): Flow<List<ObjectiveCompletion>> {
            return dao.getDailyCompletions(date)
        }

        /**
         * Obtiene la completación de un objetivo en una fecha específica
         */
        fun getCompletion(
            objectiveId: Long,
            date: String,
        ): Flow<ObjectiveCompletion?> {
            return dao.getCompletion(objectiveId, date)
        }

        /**
         * Añade un nuevo objetivo al mes actual
         */
        suspend fun addObjective(
            name: String,
            yearMonth: String,
        ) {
            val maxOrder = dao.getMaxOrderIndex(yearMonth) ?: -1
            dao.insertObjective(
                MonthlyObjective(
                    name = name,
                    yearMonth = yearMonth,
                    orderIndex = maxOrder + 1,
                ),
            )
        }

        /**
         * Actualiza un objetivo existente
         */
        suspend fun updateObjective(objective: MonthlyObjective) {
            dao.updateObjective(objective)
        }

        /**
         * Elimina un objetivo
         */
        suspend fun deleteObjective(objective: MonthlyObjective) {
            dao.deleteObjective(objective)
        }

        /**
         * Alterna el estado de completación de un objetivo en una fecha específica
         */
        suspend fun toggleCompletion(
            objectiveId: Long,
            date: String,
        ) {
            val existing = dao.getCompletion(objectiveId, date).firstOrNull()

            if (existing == null) {
                dao.insertCompletion(
                    ObjectiveCompletion(
                        objectiveId = objectiveId,
                        date = date,
                        isCompleted = true,
                    ),
                )
            } else {
                if (existing.isCompleted) {
                    dao.deleteCompletionByKey(objectiveId, date)
                } else {
                    dao.insertCompletion(existing.copy(isCompleted = true))
                }
            }
        }
    }
