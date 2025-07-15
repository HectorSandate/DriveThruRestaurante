package com.example.drivethrurestaurante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.drivethrurestaurante.theme.RestauranteTheme
import com.example.drivethrurestaurante.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestauranteTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}
