package com.example.drivethrurestaurante.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.drivethrurestaurante.screens.home.HomeScreen
import com.example.drivethrurestaurante.screens.menu.MenuScreen
import com.example.drivethrurestaurante.screens.menu.OrderScreen
import com.example.drivethrurestaurante.screens.menu.OrderSummaryScreen
import com.example.drivethrurestaurante.screens.confirmation.OrderConfirmationScreen

object Routes {
    const val HOME = "home"
    const val MENU = "menu"
    const val ORDER = "order/{itemId}"
    const val ORDER_SUMMARY = "order_summary/{itemId}"
    const val ORDER_CONFIRMATION = "order_confirmation"

    fun createOrderRoute(itemId: Int) = "order/$itemId"
    fun createOrderSummaryRoute(itemId: Int) = "order_summary/$itemId"
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(navController)
        }
        composable(Routes.MENU) {
            MenuScreen(navController)
        }
        composable(
            route = Routes.ORDER,
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            OrderScreen(
                navController = navController,
                itemId = backStackEntry.arguments?.getInt("itemId") ?: 1
            )
        }
        composable(
            route = Routes.ORDER_SUMMARY,
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: 1
            OrderSummaryScreen(
                navController = navController,
                itemId = itemId,
                itemComments = "" // Por ahora sin comentarios para simplificar
            )
        }
        composable(Routes.ORDER_CONFIRMATION) {
            OrderConfirmationScreen(navController = navController)
        }
    }
}
