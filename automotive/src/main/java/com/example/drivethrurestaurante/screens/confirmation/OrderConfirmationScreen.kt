package com.example.drivethrurestaurante.screens.confirmation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import android.util.Log
import com.example.drivethrurestaurante.network.GlobalSocketManager
import com.example.drivethrurestaurante.screens.menu.CartState
@Composable
fun OrderConfirmationScreen(navController: NavController) {
    // Estado para controlar la navegación
    var hasNavigated by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    // ✅ Enviar mensaje de orden finalizada
    GlobalSocketManager.socket?.send("De AUTOMOTIVE: ORDEN_FINALIZADA")
    Log.d("WebSocket", "📤 Enviando mensaje: ORDEN_FINALIZADA")

    // ✅ Limpiar carrito
    CartState.clearCart()
    Log.d("CartState", "🧹 Carrito limpiado")
    
    // Mostrar botón después de 10 segundos en lugar de navegar automáticamente
    LaunchedEffect(Unit) {
        delay(10000)

        showButton = true

        // Intentar navegar
        try {
            navController.navigate("home") {
                popUpTo("order_confirmation") { inclusive = true }
            }
        } catch (e: Exception) {
            Log.e("OrderConfirmation", "Navegación automática falló: ${e.message}", e)
        }

        showButton = true
        
        // También intentar navegación automática como respaldo
        try {
            Log.d("OrderConfirmation", "Intentando navegación automática")
            navController.navigate("home") {
                popUpTo("order_confirmation") { inclusive = true }
            }
        } catch (e: Exception) {
            Log.e("OrderConfirmation", "Navegación automática falló: ${e.message}", e)
            // Si falla, solo mostrar el botón
        }
    }
    
    // Cleanup cuando el composable se destruye
    DisposableEffect(Unit) {
        onDispose {
            Log.d("OrderConfirmation", "OrderConfirmationScreen disposed")
        }
    }
    
    val currentDate = remember {
        SimpleDateFormat("yy/MM/dd", Locale.getDefault()).format(Date())
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Círculo grande con check
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(0.dp),
                contentAlignment = Alignment.Center
            ) {
                val circleSize = with(LocalDensity.current) { 350.dp.toPx() }
                Canvas(modifier = Modifier.size(350.dp)) {
                    drawCircle(
                        color = Color(0xFFEF4A5A),
                        radius = size.minDimension / 2f
                    )
                    // Check grueso
                    val start = Offset(x = size.width * 0.28f, y = size.height * 0.55f)
                    val mid = Offset(x = size.width * 0.45f, y = size.height * 0.72f)
                    val end = Offset(x = size.width * 0.75f, y = size.height * 0.35f)
                    drawLine(
                        color = Color.White,
                        start = start,
                        end = mid,
                        strokeWidth = 12.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = Color.White,
                        start = mid,
                        end = end,
                        strokeWidth = 12.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
            Spacer(modifier = Modifier.width(48.dp))
            // Textos
            Column(
                modifier = Modifier.weight(1.2f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Orden confirmada!",
                    fontSize = 54.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Avanza a la siguiente ventanilla para\npagar y recoger tu orden.",
                    fontSize = 28.sp,
                    color = Color.Black,
                    lineHeight = 36.sp
                )
            }
        }
        // Fecha y teléfono
        Text(
            text = currentDate,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 56.dp, bottom = 48.dp)
        )
        Text(
            text = "0123-243-6578",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 56.dp, bottom = 48.dp)
        )
        // Icono translúcido
        
        // Botón para continuar (aparece después de 10 segundos)
        if (showButton) {
            Button(
                onClick = {
                    try {
                        Log.d("OrderConfirmation", "Usuario presionó botón para continuar")
                        navController.navigate("home") {
                            popUpTo("order_confirmation") { inclusive = true }
                        }
                    } catch (e: Exception) {
                        Log.e("OrderConfirmation", "Error en navegación manual: ${e.message}", e)
                        navController.navigate("menu")
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEF4A5A)
                )
            ) {
                Text(
                    text = "Continuar",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
} 