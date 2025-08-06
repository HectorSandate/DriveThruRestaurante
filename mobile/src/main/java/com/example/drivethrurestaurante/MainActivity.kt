package com.example.drivethrurestaurante

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.drivethrurestaurante.theme.RestauranteTheme
import com.example.drivethrurestaurante.ui.navigation.AppNavigation
import com.example.drivethrurestaurante.ui.navigation.Routes
import com.google.gson.Gson
import com.example.drivethrurestaurante.data.model.CartViewModel
import com.example.drivethrurestaurante.data.model.MenuData
import com.example.drivethrurestaurante.data.model.ServerCartItem
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    private lateinit var socket: WebSocketManager
    private val gson = Gson()

    private lateinit var cartViewModel: CartViewModel
    private var navController: androidx.navigation.NavController? = null
    private var isOnFinalScreen = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]


        socket = WebSocketManager(
            serverUrl = "ws://192.168.1.74:8080/chat", // Usa la IP de tu PC
            onConnected = {
                Log.d("WebSocket", "✅ Móvil Conectado al servidor")
                socket.send("MOVIL")
            },
            onMessageReceived = { message ->
                Log.d("WebSocket", "📩 Mensaje recibido: $message")
                
                try {
                    // Verificar si es un mensaje de orden finalizada
                    if (message.startsWith("De AUTOMOTIVE: ORDEN_FINALIZADA")) {
                        Log.d("WebSocket", "🎉 Orden finalizada recibida del automotive")
                        // Limpiar el carrito del móvil
                        cartViewModel.clearCart()
                        Log.d("CartViewModel", "🧹 Carrito del móvil limpiado por orden finalizada")
                        
                        // Solo navegar si no estamos ya en la pantalla final
                        if (!isOnFinalScreen) {
                            runOnUiThread {
                                navController?.navigate(Routes.FINAL) {
                                    // Limpiar el back stack para que no pueda volver atrás
                                    popUpTo(Routes.MENU) {
                                        inclusive = true
                                    }
                                }
                            }
                        } else {
                            Log.d("WebSocket", "ℹ️ Ya estamos en la pantalla final, no navegando de nuevo")
                        }
                    } else if (message.startsWith("De AUTOMOTIVE: ")) {
                        // Intentar extraer JSON del mensaje
                        val jsonData = message.removePrefix("De AUTOMOTIVE: ").trim()
                        try {
                            // Parsear el JSON a la nueva data class
                            val serverItems = gson.fromJson(jsonData, Array<ServerCartItem>::class.java).toList()
                            Log.d("WebSocket", "🛒 Carrito recibido del servidor: $serverItems")
                            Log.d("WebSocket", "📊 Detalles de items recibidos:")
                            serverItems.forEach { item ->
                                Log.d("WebSocket", "   - ID: ${item.id}, Nombre: ${item.name}, Cantidad: ${item.quantity}, Instrucciones: '${item.specialInstructions}'")
                            }

                            // Ejecutar la actualización del carrito en el hilo principal para garantizar sincronización con la UI
                            runOnUiThread {
                                cartViewModel.updateCartFromServerData(serverItems)
                                Log.d("WebSocket", "✅ Carrito actualizado con ${serverItems.size} productos del servidor")
                            }

                        } catch (e: Exception) {
                            Log.e("WebSocket", "Error al parsear JSON del servidor: ${e.message}")
                            Log.e("WebSocket", "JSON recibido: $jsonData")
                        }
                    }
                } catch (e: Exception) {
                    Log.e("WebSocket", "❌ Error general procesando mensaje: ${e.message}", e)
                }
            },
            onError = {
                Log.e("WebSocket", "❌ Error móvil: ${it.message}")
            }
        )

        socket.connect()

        // Configurar el WebSocket en el CartViewModel para envío bidireccional
        cartViewModel.setWebSocketManager(socket)

        enableEdgeToEdge()

        setContent {
            RestauranteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    this@MainActivity.navController = navController
                    
                    // Configurar listener para detectar cuando estamos en la pantalla final
                    androidx.compose.runtime.DisposableEffect(Unit) {
                        val listener = androidx.navigation.NavController.OnDestinationChangedListener { _, destination, _ ->
                            isOnFinalScreen = destination.route == Routes.FINAL
                            Log.d("Navigation", "🔄 Navegando a: ${destination.route}, isOnFinalScreen: $isOnFinalScreen")
                        }
                        navController.addOnDestinationChangedListener(listener)
                        onDispose {
                            navController.removeOnDestinationChangedListener(listener)
                        }
                    }
                    
                    AppNavigation(navController = navController, cartViewModel = cartViewModel, webSocketManager = socket)
                }
            }
        }
    }
}