package com.dennnisver4.habithub.data.repository

import com.dennnisver4.habithub.data.DailyNote
import com.dennnisver4.habithub.data.HabitHubDao
import kotlinx.coroutines.flow.Flow

class MonthlyCalendarRepository(
    private val dao: HabitHubDao
) {

    fun getDailyNote(date: String): Flow<DailyNote?> = dao.getDailyNote(date)

    fun getMonthlyNotes(yearMonth: String): Flow<List<DailyNote>> = dao.getMonthlyNotes(yearMonth)

    suspend fun insertDailyNote(note: DailyNote) = dao.insertDailyNote(note)

    suspend fun deleteDailyNote(note: DailyNote) = dao.deleteDailyNote(note)

}
