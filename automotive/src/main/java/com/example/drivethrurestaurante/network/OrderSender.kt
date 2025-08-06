package com.example.drivethrurestaurante.network

import android.util.Log
import com.example.drivethrurestaurante.screens.menu.CartState

object OrderSender {
    fun enviarOrdenFinalizada() {
        val mensaje = "AUTOMOTIVE: ORDEN_FINALIZADA"
        GlobalSocketManager.socket?.send(mensaje)
        Log.d("WebSocket", "📤 Enviando orden finalizada: $mensaje")
        CartState.clearCart()
        Log.d("CartState", "🧹 Carrito limpiado después de confirmar orden")
    }
}
