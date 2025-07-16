package com.example.drivethrurestaurante.screens.menu

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class CartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int,
    val comments: String = ""
)

object CartState {
    private var cartItems by mutableStateOf<List<CartItem>>(emptyList())
    
    fun addItem(item: CartItem) {
        val existingItem = cartItems.find { it.id == item.id }
        if (existingItem != null) {
            // Si el item ya existe, actualizar la cantidad
            val updatedItems = cartItems.map { 
                if (it.id == item.id) {
                    it.copy(quantity = it.quantity + item.quantity)
                } else {
                    it
                }
            }
            cartItems = updatedItems
        } else {
            cartItems = cartItems + item
        }
    }
    
    fun removeItem(itemId: Int) {
        cartItems = cartItems.filter { it.id != itemId }
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
    }
    
    fun getItems(): List<CartItem> = cartItems
    
    fun getTotalItems(): Int = cartItems.sumOf { it.quantity }
    
    fun getTotalPrice(): Double = cartItems.sumOf { it.price * it.quantity }
    
    fun clearCart() {
        cartItems = emptyList()
    }
} 