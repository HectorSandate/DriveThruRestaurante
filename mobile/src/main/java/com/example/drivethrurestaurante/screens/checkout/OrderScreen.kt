package com.example.drivethrurestaurante.screens.order

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.drivethrurestaurante.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Pedido") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Aquí puedes agregar el contenido del pedido
            Text(
                text = "Resumen del pedido",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ejemplo de items del pedido
            Text("• Hamburguesa clásica - $5.99")
            Text("• Papas fritas - $2.99")
            Text("• Refresco - $1.99")

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    navController.navigate(Routes.CONFIRMATION)
                }
            ) {
                Text("Confirmar pedido")
            }
        }
    }
}