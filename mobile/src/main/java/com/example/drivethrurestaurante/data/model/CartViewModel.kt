// CartViewModel.kt
package com.example.drivethrurestaurante.data.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import android.util.Log

data class CartItem(
    val menuItem: MenuItem,
    val quantity: Int = 1,
    val specialInstructions: String = ""
)

class CartViewModel : ViewModel() {
    var cartItems by mutableStateOf<List<CartItem>>(emptyList())
        private set
    
    // Función para forzar la actualización de la UI
    private fun forceUIUpdate() {
        // Crear una nueva referencia de la lista para forzar la recomposición
        val currentItems = cartItems.toList()
        cartItems = emptyList()
        cartItems = currentItems
    }

    // Hacer estas variables públicas para acceso desde las pantallas
    var showOrderDialog by mutableStateOf(false)

    var currentAddedItem by mutableStateOf<CartItem?>(null)

    // Referencia al WebSocket para enviar datos
    private var webSocketManager: com.example.drivethrurestaurante.WebSocketManager? = null
    private val gson = Gson()
    
    // Flag para evitar envío automático cuando se reciben datos del servidor
    private var isUpdatingFromServer = false

    // Función para configurar el WebSocket
    fun setWebSocketManager(manager: com.example.drivethrurestaurante.WebSocketManager) {
        webSocketManager = manager
    }

    // Función privada para enviar carrito al servidor
    private fun sendCartToServer() {
        if (isUpdatingFromServer) {
            Log.d("CartViewModel", "⏸️ Omitiendo envío automático (actualizando desde servidor)")
            return
        }
        
        webSocketManager?.let { socket ->
            try {
                // Convertir CartItem a MobileCartItem para enviar
                val mobileItems = cartItems.map { cartItem ->
                    MobileCartItem(
                        id = cartItem.menuItem.id,
                        name = cartItem.menuItem.name,
                        price = cartItem.menuItem.price,
                        quantity = cartItem.quantity,
                        comments = cartItem.specialInstructions
                    )
                }
                
                val json = gson.toJson(mobileItems)
                val message = "De MOVIL: $json"
                
                Log.d("CartViewModel", "📤 Enviando carrito al servidor: $message")
                socket.send(message)
            } catch (e: Exception) {
                Log.e("CartViewModel", "❌ Error enviando carrito al servidor: ${e.message}")
            }
        } ?: run {
            Log.w("CartViewModel", "⚠️ WebSocket no disponible para enviar carrito")
        }
    }

    // Función para enviar mensaje de orden finalizada
    fun sendOrderFinalized() {
        webSocketManager?.let { socket ->
            try {
                val message = "De MOVIL: ORDEN_FINALIZADA"
                Log.d("CartViewModel", "📤 Enviando orden finalizada al servidor: $message")
                socket.send(message)
            } catch (e: Exception) {
                Log.e("CartViewModel", "❌ Error enviando orden finalizada al servidor: ${e.message}")
            }
        } ?: run {
            Log.w("CartViewModel", "⚠️ WebSocket no disponible para enviar orden finalizada")
        }
    }

    // Variables para el modal de detalles del producto
    var showProductDetailsDialog by mutableStateOf(false)

    var selectedMenuItem by mutableStateOf<MenuItem?>(null)

    // Agregar producto al carrito
    fun addToCart(menuItem: MenuItem, quantity: Int = 1, specialInstructions: String = "", showConfirmationDialog: Boolean = true) {
        val existingItemIndex = cartItems.indexOfFirst { it.menuItem.id == menuItem.id }

        if (existingItemIndex >= 0) {
            // Si el producto ya existe, actualizar cantidad
            val updatedItems = cartItems.toMutableList()
            val existingItem = updatedItems[existingItemIndex]
            updatedItems[existingItemIndex] = existingItem.copy(
                quantity = existingItem.quantity + quantity,
                specialInstructions = if (specialInstructions.isNotEmpty()) specialInstructions else existingItem.specialInstructions
            )
            cartItems = updatedItems
            currentAddedItem = updatedItems[existingItemIndex]
        } else {
            // Si es nuevo, agregarlo
            val newItem = CartItem(menuItem, quantity, specialInstructions)
            cartItems = cartItems + newItem
            currentAddedItem = newItem
        }

        // Mostrar diálogo de confirmación solo si se solicita
        if (showConfirmationDialog) {
            showOrderDialog = true
        }

        // Enviar carrito actualizado al servidor
        sendCartToServer()
    }

    // Actualizar cantidad de un producto
    fun updateQuantity(menuItemId: Int, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeFromCart(menuItemId)
        } else {
            cartItems = cartItems.map { cartItem ->
                if (cartItem.menuItem.id == menuItemId) {
                    cartItem.copy(quantity = newQuantity)
                } else {
                    cartItem
                }
            }
            // Enviar carrito actualizado al servidor
            sendCartToServer()
        }
    }

    // Eliminar producto del carrito
    fun removeFromCart(menuItemId: Int) {
        cartItems = cartItems.filter { it.menuItem.id != menuItemId }
        // Enviar carrito actualizado al servidor
        sendCartToServer()
    }

    // Limpiar carrito
    fun clearCart() {
        cartItems = emptyList()
        // Enviar carrito vacío al servidor
        sendCartToServer()
    }

    // Cerrar diálogo de orden
    fun dismissOrderDialog() {
        showOrderDialog = false
        currentAddedItem = null
    }

    // Mostrar modal de detalles del producto
    fun showProductDetails(menuItem: MenuItem) {
        selectedMenuItem = menuItem
        showProductDetailsDialog = true
    }

    // Cerrar modal de detalles del producto
    fun dismissProductDetailsDialog() {
        showProductDetailsDialog = false
        selectedMenuItem = null
    }

    // Obtener total del carrito
    fun getTotal(): Double {
        return cartItems.sumOf { it.menuItem.price * it.quantity }
    }

    // Obtener cantidad total de items
    fun getTotalItems(): Int {
        return cartItems.sumOf { it.quantity }
    }

    // Actualizar instrucciones especiales
    fun updateSpecialInstructions(menuItemId: Int, instructions: String) {
        cartItems = cartItems.map { cartItem ->
            if (cartItem.menuItem.id == menuItemId) {
                cartItem.copy(specialInstructions = instructions)
            } else {
                cartItem
            }
        }
        // Enviar carrito actualizado al servidor
        sendCartToServer()
    }

    fun updateCartFromReceivedItems(receivedItems: List<CartItem>) {
        receivedItems.forEach { receivedItem ->
            val menuItem = MenuData.menuItems.find { it.id == receivedItem.menuItem.id }
            if (menuItem != null) {
                // Usa addToCart para agregar o sumar cantidades
                addToCart(
                    menuItem = menuItem,
                    quantity = receivedItem.quantity,
                    specialInstructions = receivedItem.specialInstructions,
                    showConfirmationDialog = false // No mostrar diálogo para cada uno
                )
            } else {
                // Opcional: log o manejo si no existe el item en menú
                println("Item con id ${receivedItem.menuItem.id} no existe en menú.")
            }
        }
    }

    // Método para actualizar el carrito desde datos del servidor
    fun updateCartFromServerData(serverItems: List<com.example.drivethrurestaurante.data.model.ServerCartItem>) {
        android.util.Log.d("CartViewModel", "🔄 Actualizando carrito con ${serverItems.size} items del servidor")
        
        isUpdatingFromServer = true
        
        // Crear una nueva lista mutable para evitar problemas de concurrencia
        val newCartItems = mutableListOf<CartItem>()
        
        // Agregar los items del servidor a la nueva lista
        serverItems.forEach { serverItem ->
            val menuItem = MenuData.menuItems.find { it.id == serverItem.id }
            if (menuItem != null) {
                android.util.Log.d("CartViewModel", "✅ Agregando al carrito: ${menuItem.name} x${serverItem.quantity} - Instrucciones: '${serverItem.specialInstructions}'")
                
                // Crear el CartItem directamente sin usar addToCart
                val cartItem = CartItem(
                    menuItem = menuItem,
                    quantity = serverItem.quantity,
                    specialInstructions = serverItem.specialInstructions
                )
                
                newCartItems.add(cartItem)
            } else {
                android.util.Log.e("CartViewModel", "❌ Item con id ${serverItem.id} no existe en menú local.")
            }
        }
        
        // Asignar la nueva lista completa de una vez para garantizar actualización inmediata
        cartItems = newCartItems.toList()
        
        isUpdatingFromServer = false
        android.util.Log.d("CartViewModel", "📊 Carrito actualizado. Total items: ${getTotalItems()}")
        
        // Forzar la actualización de la UI después de un breve delay
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            forceUIUpdate()
            android.util.Log.d("CartViewModel", "🔄 UI forzada a actualizar")
        }, 100) // 100ms de delay para forzar la UI
        
        // Verificar que la actualización se reflejó correctamente
        verifyCartUpdate(serverItems)
    }
    
    // Método para verificar que la actualización del carrito se reflejó correctamente
    private fun verifyCartUpdate(expectedServerItems: List<com.example.drivethrurestaurante.data.model.ServerCartItem>) {
        // Programar una verificación después de un breve delay
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            val currentItems = cartItems.size
            val expectedItems = expectedServerItems.size
            
            if (currentItems != expectedItems) {
                android.util.Log.w("CartViewModel", "⚠️ Verificación fallida: esperados $expectedItems items, actuales $currentItems")
                // Reintentar la actualización
                android.util.Log.d("CartViewModel", "🔄 Reintentando actualización del carrito...")
                updateCartFromServerData(expectedServerItems)
            } else {
                android.util.Log.d("CartViewModel", "✅ Verificación exitosa: $currentItems items en carrito")
            }
        }, 500) // 500ms de delay para la verificación
    }
}