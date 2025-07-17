package com.example.drivethrurestaurante.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.drivethrurestaurante.screens.home.HomeScreen
import com.example.drivethrurestaurante.screens.menu.MenuScreen
import com.example.drivethrurestaurante.screens.checkout.OrderScreen
import com.example.drivethrurestaurante.screens.confirmation.ConfirmationScreen
import com.example.drivethrurestaurante.screens.finalscreen.FinalScreen

object Routes {
    const val HOME = "home"
    const val MENU = "menu"
    const val ORDER = "order"
    const val CONFIRMATION = "confirmation"
    const val FINAL = "final"
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) { HomeScreen(navController) }
        composable(Routes.MENU) { MenuScreen(navController) }
        composable(Routes.ORDER) { OrderScreen(navController) }
        composable(Routes.CONFIRMATION) { ConfirmationScreen(navController) }
        composable(Routes.FINAL) { FinalScreen(navController) }
    }
}
