package com.dennnisver4.habithub.ui.navigation

sealed class Screen(val route: String) {
    object MonthlyCalendar : Screen("monthly_calendar")
    object ObjectivesTable : Screen("objectives_table")
    object ProgressChart : Screen("progress_chart")
    object Settings : Screen("settings")
}
