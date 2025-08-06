package com.example.drivethrurestaurante.screens.finalscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.drivethrurestaurante.ui.navigation.Routes
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalScreen(navController: NavController) {
    // Estado para controlar la visibilidad del botón
    var showOrderAgainButton by remember { mutableStateOf(false) }
    
    // Obtener la fecha actual
    val currentDate = SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(Date())
    
    // Mostrar el botón después de 10 segundos (solo una vez)
    LaunchedEffect(Unit) {
        delay(10000) // 10 segundos
        showOrderAgainButton = true
    }
    
    Scaffold(

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Contenido principal centrado
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Círculo rojo con checkmark
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF44336)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Confirmado",
                        modifier = Modifier.size(60.dp),
                        tint = Color.White
                    )
                }
                
                // Texto de confirmación
                Text(
                    text = "Orden confirmada!",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                
                // Instrucciones
                Text(
                    text = "Avanza a la siguiente ventanilla para pagar y recoger tu orden.",
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Fecha y teléfono centrados
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Fecha
                    Text(
                        text = currentDate,
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Normal
                    )
                    // Número de teléfono
                    Text(
                        text = "Pedido #0123-001",
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Normal
                    )
                }
                
                // Botón "Volver a ordenar" que aparece después de 10 segundos
                if (showOrderAgainButton) {
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    Button(
                        onClick = {
                            // Navegar al inicio de la app (HOME)
                            navController.navigate(Routes.HOME) {
                                // Limpiar todo el back stack para que no pueda volver atrás
                                popUpTo(0) { inclusive = true }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        /*Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )*/
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Volver a ordenar",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}