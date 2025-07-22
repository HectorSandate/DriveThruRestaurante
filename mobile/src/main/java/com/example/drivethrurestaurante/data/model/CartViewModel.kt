// CartViewModel.kt
package com.example.drivethrurestaurante.data.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class CartItem(
    val menuItem: MenuItem,
    val quantity: Int = 1,
    val specialInstructions: String = ""
)

class CartViewModel : ViewModel() {
    var cartItems by mutableStateOf<List<CartItem>>(emptyList())
        private set

    // Hacer estas variables públicas para acceso desde las pantallas
    var showOrderDialog by mutableStateOf(false)

    var currentAddedItem by mutableStateOf<CartItem?>(null)

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
        }
    }

    // Eliminar producto del carrito
    fun removeFromCart(menuItemId: Int) {
        cartItems = cartItems.filter { it.menuItem.id != menuItemId }
    }

    // Limpiar carrito
    fun clearCart() {
        cartItems = emptyList()
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
    }
}