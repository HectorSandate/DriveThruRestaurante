package com.example.drivethrurestaurante.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.drivethrurestaurante.screens.menu.MenuScreen
import com.example.drivethrurestaurante.screens.menu.OrderScreen
import com.example.drivethrurestaurante.screens.menu.OrderSummaryScreen

object Routes {
    const val MENU = "menu"
    const val ORDER = "order/{itemId}"
    const val ORDER_SUMMARY = "order_summary/{itemId}"

    fun createOrderRoute(itemId: Int) = "order/$itemId"
    fun createOrderSummaryRoute(itemId: Int) = "order_summary/$itemId"
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.MENU
    ) {
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
                itemComments = ""  
            )
        }
    }
}
