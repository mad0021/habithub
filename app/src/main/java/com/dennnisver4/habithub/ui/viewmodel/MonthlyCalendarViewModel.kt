package com.dennnisver4.habithub.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennnisver4.habithub.data.DailyNote
import com.dennnisver4.habithub.data.repository.MonthlyCalendarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MonthlyCalendarViewModel @Inject constructor(
    private val repository: MonthlyCalendarRepository
) : ViewModel() {

    // Month selection
    private val _currentYearMonth = MutableStateFlow(YearMonth.now())
    val currentYearMonth: StateFlow<YearMonth> = _currentYearMonth.asStateFlow()

    fun navigateToMonth(yearMonth: YearMonth) {
        _currentYearMonth.value = yearMonth
    }

    fun navigateToPreviousMonth() {
        _currentYearMonth.value = _currentYearMonth.value.minusMonths(1)
    }

    fun navigateToNextMonth() {
        _currentYearMonth.value = _currentYearMonth.value.plusMonths(1)
    }

    private val yearMonthString: StateFlow<String> = currentYearMonth.map {
        it.format(DateTimeFormatter.ofPattern("yyyy-MM"))
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        "",
    )

    // Notes
    fun getDailyNote(date: LocalDate): Flow<DailyNote?> = repository.getDailyNote(date.toString())

    val monthlyNotes: StateFlow<List<DailyNote>> = yearMonthString.flatMapLatest { yearMonth ->
        if (yearMonth.isNotEmpty()) repository.getMonthlyNotes(yearMonth)
        else flowOf(emptyList())
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList(),
    )

    fun saveDailyNote(date: LocalDate, note: String) {
        viewModelScope.launch {
            repository.insertDailyNote(
                DailyNote(date = date.toString(), note = note)
            )
        }
    }

    fun deleteDailyNote(date: LocalDate) {
        viewModelScope.launch {
            repository.deleteDailyNote(DailyNote(date = date.toString(), note = ""))
        }
    }
}
