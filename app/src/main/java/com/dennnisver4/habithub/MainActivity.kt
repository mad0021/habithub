package com.dennnisver4.habithub

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dennnisver4.habithub.data.ThemeMode
import com.dennnisver4.habithub.data.ThemePreferences
import com.dennnisver4.habithub.ui.navigation.Screen
import com.dennnisver4.habithub.ui.screens.MonthlyCalendarScreen
import com.dennnisver4.habithub.ui.screens.ObjectivesTableScreen
import com.dennnisver4.habithub.ui.screens.ProgressChartScreen
import com.dennnisver4.habithub.ui.screens.SettingsScreen
import com.dennnisver4.habithub.ui.theme.HabitHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val themePreferences = remember { ThemePreferences(context) }
            val themeMode by themePreferences.themeMode.collectAsState(initial = ThemeMode.SYSTEM)
            val systemInDarkTheme = isSystemInDarkTheme()

            val darkTheme =
                when (themeMode) {
                    ThemeMode.LIGHT -> false
                    ThemeMode.DARK -> true
                    ThemeMode.SYSTEM -> systemInDarkTheme
                }

            HabitHubTheme(
                darkTheme = darkTheme,
                useOledTheme = true, // Siempre usar tema OLED cuando está en oscuro
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    HabitHubApp()
                }
            }
        }
    }
}

@Composable
fun HabitHubApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MonthlyCalendar.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(400),
            ) + fadeIn(animationSpec = tween(400))
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(400),
            ) + fadeOut(animationSpec = tween(400))
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(400),
            ) + fadeIn(animationSpec = tween(400))
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(400),
            ) + fadeOut(animationSpec = tween(400))
        },
    ) {
        composable(Screen.MonthlyCalendar.route) {
            MonthlyCalendarScreen(
                onNavigateToObjectives = {
                    navController.navigate(Screen.ObjectivesTable.route)
                },
                onNavigateToChart = {
                    navController.navigate(Screen.ProgressChart.route)
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                },
            )
        }

        composable(Screen.ObjectivesTable.route) {
            ObjectivesTableScreen(
                onNavigateToCalendar = {
                    navController.navigate(Screen.MonthlyCalendar.route) {
                        popUpTo(Screen.MonthlyCalendar.route) { inclusive = true }
                    }
                },
                onNavigateToChart = {
                    navController.navigate(Screen.ProgressChart.route)
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                },
            )
        }

        composable(Screen.ProgressChart.route) {
            ProgressChartScreen(
                onNavigateToCalendar = {
                    navController.navigate(Screen.MonthlyCalendar.route) {
                        popUpTo(Screen.MonthlyCalendar.route) { inclusive = true }
                    }
                },
                onNavigateToObjectives = {
                    navController.navigate(Screen.ObjectivesTable.route)
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                },
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
            )
        }
    }
}
