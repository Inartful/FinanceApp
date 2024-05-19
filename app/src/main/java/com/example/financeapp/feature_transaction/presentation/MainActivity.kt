package com.example.financeapp.feature_transaction.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.financeapp.feature_transaction.presentation.add_account.AddAccountScreen
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionScreen
import com.example.financeapp.feature_transaction.presentation.history.HistoryScreen
import com.example.financeapp.feature_transaction.presentation.main_screen.MainScreen
import com.example.financeapp.feature_transaction.presentation.settings.SettingsScreen
import com.example.financeapp.feature_transaction.presentation.settings.SettingsViewModel
import com.example.financeapp.feature_transaction.presentation.util.AppTheme
import com.example.financeapp.feature_transaction.presentation.util.Screen
import com.example.financeapp.ui.theme.FinanceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsViewModel: SettingsViewModel by viewModels()
            val settingsState = settingsViewModel.state

            when(settingsState.value.theme) {
                AppTheme.AutoTheme -> {
                    FinanceAppTheme(
                        darkTheme = isSystemInDarkTheme()
                    ) {
                        Navigation(settingsViewModel)
                    }
                }
                AppTheme.DarkTheme -> {
                    FinanceAppTheme(
                        darkTheme = true
                    ) {
                        Navigation(settingsViewModel)
                    }
                }
                AppTheme.LightTheme -> {
                    FinanceAppTheme(
                        darkTheme = false
                    ) {
                        Navigation(settingsViewModel)
                    }
                }
            }
        }
    }
}
@Composable
fun Navigation(
    settingsViewModel: SettingsViewModel
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.MainScreen.route
        ) {
            composable(route = Screen.MainScreen.route) {
                MainScreen(navController = navController)
            }

            composable(route = Screen.Settings.route) {
                SettingsScreen(
                    navController = navController,
                    viewModel = settingsViewModel
                )
            }

            composable(
                route = Screen.AddTransaction.route + "/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                        nullable = false
                    }
                )
            ) { entry ->
                AddTransactionScreen(
                    navController = navController,
                    transactionId = entry.arguments?.getInt("id") ?: -1
                )
            }

            composable(
                route = Screen.AddAccount.route + "/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                        nullable = false
                    }
                )
            ) {entry ->
                AddAccountScreen(navController = navController,
                    accountId = entry.arguments?.getInt("id") ?: -1)
            }
            
            composable(
                route = Screen.History.route
            ) {
                HistoryScreen(navController = navController)
            }
        }
    }
}
