package com.dennnisver4.habithub.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitHubDao {
    // ========== DAILY NOTES ==========
    @Query("SELECT * FROM daily_notes WHERE date = :date")
    fun getDailyNote(date: String): Flow<DailyNote?>

    @Query("SELECT * FROM daily_notes WHERE date LIKE :yearMonth || '%' ORDER BY date")
    fun getMonthlyNotes(yearMonth: String): Flow<List<DailyNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyNote(note: DailyNote)

    @Delete
    suspend fun deleteDailyNote(note: DailyNote)

    // ========== MONTHLY OBJECTIVES ==========
    @Query("SELECT * FROM monthly_objectives WHERE yearMonth = :yearMonth ORDER BY orderIndex ASC")
    fun getMonthlyObjectives(yearMonth: String): Flow<List<MonthlyObjective>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertObjective(objective: MonthlyObjective): Long

    @Update
    suspend fun updateObjective(objective: MonthlyObjective)

    @Delete
    suspend fun deleteObjective(objective: MonthlyObjective)

    @Query("SELECT MAX(orderIndex) FROM monthly_objectives WHERE yearMonth = :yearMonth")
    suspend fun getMaxOrderIndex(yearMonth: String): Int?

    // ========== OBJECTIVE COMPLETIONS ==========
    @Query("SELECT * FROM objective_completions WHERE objectiveId = :objectiveId AND date = :date")
    fun getCompletion(
        objectiveId: Long,
        date: String,
    ): Flow<ObjectiveCompletion?>

    @Query("SELECT * FROM objective_completions WHERE date = :date")
    fun getDailyCompletions(date: String): Flow<List<ObjectiveCompletion>>

    @Query(
        """
        SELECT * FROM objective_completions 
        WHERE objectiveId IN (:objectiveIds) 
        AND date LIKE :yearMonth || '%'
        ORDER BY date ASC
    """,
    )
    fun getMonthlyCompletions(
        objectiveIds: List<Long>,
        yearMonth: String,
    ): Flow<List<ObjectiveCompletion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompletion(completion: ObjectiveCompletion)

    @Delete
    suspend fun deleteCompletion(completion: ObjectiveCompletion)

    @Query("DELETE FROM objective_completions WHERE objectiveId = :objectiveId AND date = :date")
    suspend fun deleteCompletionByKey(
        objectiveId: Long,
        date: String,
    )

    // ========== STATISTICS ==========
    @Query(
        """
        SELECT date, COUNT(*) as count 
        FROM objective_completions 
        WHERE objectiveId IN (:objectiveIds) 
        AND date LIKE :yearMonth || '%' 
        AND isCompleted = 1
        GROUP BY date
        ORDER BY date ASC
    """,
    )
    fun getMonthlyCompletionCounts(
        objectiveIds: List<Long>,
        yearMonth: String,
    ): Flow<List<DailyCompletionCount>>
}

// Clase auxiliar para las estadísticas
data class DailyCompletionCount(
    val date: String,
    val count: Int,
)
