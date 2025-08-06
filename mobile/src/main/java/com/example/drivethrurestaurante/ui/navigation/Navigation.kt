package com.example.drivethrurestaurante.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.drivethrurestaurante.screens.home.HomeScreen
import com.example.drivethrurestaurante.screens.menu.MenuScreen
import com.example.drivethrurestaurante.screens.checkout.OrderScreen
import com.example.drivethrurestaurante.screens.confirmation.ConfirmationScreen
import com.example.drivethrurestaurante.screens.finalscreen.FinalScreen
import com.example.drivethrurestaurante.data.model.CartViewModel

object Routes {
    const val HOME = "home"
    const val MENU = "menu"
    const val ORDER = "order"
    const val CONFIRMATION = "confirmation"
    const val FINAL = "final"
}

@Composable
fun AppNavigation(navController: NavHostController, cartViewModel: CartViewModel, webSocketManager: com.example.drivethrurestaurante.WebSocketManager? = null) {
    // Crear una instancia compartida del CartViewModel
    //val cartViewModel = yourParamCartViewModel

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(navController, webSocketManager)
        }
        composable(Routes.MENU) {
            MenuScreen(navController, cartViewModel)
        }
        composable(Routes.ORDER) {
            OrderScreen(navController, cartViewModel)
        }
        composable(Routes.CONFIRMATION) {
            ConfirmationScreen(navController, cartViewModel)
        }
        composable(Routes.FINAL) {
            FinalScreen(navController)
        }
    }
}