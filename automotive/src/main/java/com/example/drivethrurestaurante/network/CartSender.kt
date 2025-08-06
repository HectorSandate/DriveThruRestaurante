package com.example.drivethrurestaurante.network

import android.util.Log
import com.example.drivethrurestaurante.network.WebSocketManager
import com.example.drivethrurestaurante.screens.menu.CartState
import com.google.gson.Gson

object CartSender {
    fun enviarCarrito(socket: WebSocketManager) {
        val carrito = CartState.getItems()
        val json = Gson().toJson(carrito)
        val mensajeFinal = "De AUTOMOTIVE: $json"
        Log.d("WebSocket", "📤 Enviando carrito: $mensajeFinal")
        socket.send(mensajeFinal)
    }
}