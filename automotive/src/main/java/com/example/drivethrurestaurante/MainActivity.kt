package com.example.drivethrurestaurante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.drivethrurestaurante.theme.RestauranteTheme
import com.example.drivethrurestaurante.ui.navigation.Navigation
import com.example.drivethrurestaurante.network.WebSocketManager
import android.util.Log
import com.example.drivethrurestaurante.network.CartSender
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import com.example.drivethrurestaurante.network.*
import com.example.drivethrurestaurante.network.GlobalSocketManager
import com.example.drivethrurestaurante.screens.menu.CartState
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    private lateinit var socket: WebSocketManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        socket = WebSocketManager(
            serverUrl = "ws://10.0.2.2:8080/chat", // Usa la IP de tu PC
            onConnected = {
                Log.d("WebSocket", "✅ Conectado al servidor")
                socket.send("AUTOMOTIVE")

                GlobalSocketManager.socket = socket

                // El envío automático ahora se maneja en CartState cuando se modifica el carrito
                Log.d("WebSocket", "✅ Envío automático configurado en CartState")
            },
            onMessageReceived = { message: String ->
                Log.d("WebSocket", "📩 Mensaje recibido: $message")
                
                                try {
                    // Verificar si es un mensaje de orden finalizada
                    if (message.startsWith("De MOVIL: ORDEN_FINALIZADA")) {
                        Log.d("WebSocket", "🎉 Orden finalizada recibida del móvil")
                        // Ejecutar el callback de orden finalizada en el hilo principal
                        runOnUiThread {
                            GlobalSocketManager.onOrderFinalized?.invoke()
                        }
                    } else if (message.startsWith("De MOVIL: NAVEGAR_A_MENU")) {
                        Log.d("WebSocket", "📱 Navegación al menú solicitada por móvil")
                        // Ejecutar el callback de navegación al menú en el hilo principal
                        runOnUiThread {
                            GlobalSocketManager.onNavigateToMenu?.invoke()
                        }
                    } else if (message.startsWith("De MOVIL: ")) {
                // Procesar mensajes del móvil
                    val jsonData = message.removePrefix("De MOVIL: ").trim()
                    try {
                        val mobileItems = Gson().fromJson(jsonData, Array<MobileCartItem>::class.java).toList()
                        Log.d("WebSocket", "🛒 Carrito recibido del móvil: $mobileItems")
                        
                        // Actualizar el carrito del automotive con los datos del móvil
                        updateCartFromMobile(mobileItems)
                        
                    } catch (e: Exception) {
                        Log.e("WebSocket", "Error al parsear JSON del móvil: ${e.message}")
                    }
                    }
                } catch (e: Exception) {
                    Log.e("WebSocket", "❌ Error general procesando mensaje: ${e.message}", e)
                }
            },
            onError = { error: Throwable ->
                Log.e("WebSocket", "❌ Error: ${error.message}")
            }
        )

        socket.connect()

        setContent {
            AppContent()
        }
    }

    // Función para actualizar el carrito del automotive con datos del móvil
    private fun updateCartFromMobile(mobileItems: List<MobileCartItem>) {
        Log.d("WebSocket", "🔄 Actualizando carrito del automotive con ${mobileItems.size} items del móvil")
        
        CartState.updateFromMobile {
            // Limpiar el carrito actual
            CartState.clearCart()
            
            // Agregar los items del móvil
            mobileItems.forEach { mobileItem ->
                val cartItem = com.example.drivethrurestaurante.screens.menu.CartItem(
                    id = mobileItem.id,
                    name = mobileItem.name,
                    price = mobileItem.price,
                    quantity = mobileItem.quantity,
                    comments = mobileItem.comments
                )
                CartState.addItem(cartItem)
                Log.d("WebSocket", "✅ Item agregado al automotive: ${mobileItem.name} x${mobileItem.quantity}")
            }
        }
        
        Log.d("WebSocket", "📊 Carrito del automotive actualizado. Total items: ${CartState.getTotalItems()}")
    }
}

@Composable
fun AppContent() {
    RestauranteTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            
            // Configurar el callback para orden finalizada
            androidx.compose.runtime.DisposableEffect(Unit) {
                GlobalSocketManager.onOrderFinalized = {
                    // Limpiar el carrito del automotive
                    CartState.clearCart()
                    Log.d("CartState", "🧹 Carrito del automotive limpiado por orden finalizada")
                    
                    // Navegar a la pantalla de confirmación
                    navController.navigate("order_confirmation") {
                        // Limpiar el back stack para que no pueda volver atrás
                        popUpTo("menu") {
                            inclusive = false
                        }
                    }
                }
                
                // Configurar el callback para navegación al menú
                GlobalSocketManager.onNavigateToMenu = {
                    Log.d("Navigation", "📱 Navegando al menú por solicitud del móvil")
                    navController.navigate("menu") {
                        // Limpiar el back stack para que no pueda volver atrás
                        popUpTo("home") {
                            inclusive = false
                        }
                    }
                }
                
                onDispose {
                    GlobalSocketManager.onOrderFinalized = null
                    GlobalSocketManager.onNavigateToMenu = null
                }
            }
            
            Navigation(navController = navController)
        }
    }
} 