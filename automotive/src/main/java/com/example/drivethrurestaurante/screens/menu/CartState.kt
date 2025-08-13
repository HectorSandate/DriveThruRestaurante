package com.example.drivethrurestaurante.screens.menu

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.drivethrurestaurante.network.CartSender
import com.example.drivethrurestaurante.network.GlobalSocketManager
import android.util.Log

data class CartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int,
    val comments: String = ""
)

object CartState {
    private var cartItems by mutableStateOf<List<CartItem>>(emptyList())
    private var isUpdatingFromMobile = false // Flag para evitar envío automático cuando se reciben datos del móvil

    // Función privada para enviar automáticamente al móvil
    private fun sendToMobile() {
        if (isUpdatingFromMobile) {
            Log.d("CartState", "⏸️ Omitiendo envío automático (actualizando desde móvil)")
            return
        }
        
        GlobalSocketManager.socket?.let { socket ->
            Log.d("CartState", "📤 Enviando carrito automáticamente al móvil")
            CartSender.enviarCarrito(socket)
        } ?: run {
            Log.w("CartState", "⚠️ Socket no disponible para enviar al móvil")
        }
    }

    // Función para actualizar desde el móvil sin enviar de vuelta
    fun updateFromMobile(updateFunction: () -> Unit) {
        isUpdatingFromMobile = true
        updateFunction()
        isUpdatingFromMobile = false
    }

    fun addItem(item: CartItem) {
        val existingItem = cartItems.find { it.id == item.id }
        if (existingItem != null) {
            // Si el item ya existe, actualizar la cantidad y el comentario
            val updatedItems = cartItems.map {
                if (it.id == item.id) {
                    it.copy(
                        quantity = it.quantity + item.quantity,
                        comments = item.comments
                    )
                } else {
                    it
                }
            }
            cartItems = updatedItems
        } else {
            cartItems = cartItems + item
        }
        
        // Enviar automáticamente al móvil después de agregar/actualizar
        sendToMobile()
    }

    fun removeItem(itemId: Int) {
        cartItems = cartItems.filter { it.id != itemId }
        // Enviar automáticamente al móvil después de eliminar
        sendToMobile()
    }

    fun updateItemQuantity(itemId: Int, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeItem(itemId)
        } else {
            cartItems = cartItems.map {
                if (it.id == itemId) {
                    it.copy(quantity = newQuantity)
                } else {
                    it
                }
            }
            // Enviar automáticamente al móvil después de actualizar cantidad
            sendToMobile()
        }
    }

    fun updateItemComments(itemId: Int, newComments: String) {
        cartItems = cartItems.map {
            if (it.id == itemId) {
                it.copy(comments = newComments)
            } else {
                it
            }
        }
        // Enviar automáticamente al móvil después de actualizar comentarios
        sendToMobile()
    }

    fun getItems(): List<CartItem> = cartItems

    fun getTotalItems(): Int = cartItems.sumOf { it.quantity }

    fun getTotalPrice(): Double = cartItems.sumOf { it.price * it.quantity }

    fun clearCart() {
        cartItems = emptyList()
        // Enviar automáticamente al móvil después de limpiar (carrito vacío)
        sendToMobile()
    }
} 