package com.example.drivethrurestaurante.network

import com.example.drivethrurestaurante.network.WebSocketManager

object GlobalSocketManager {
    var socket: WebSocketManager? = null
    var onOrderFinalized: (() -> Unit)? = null
    var onNavigateToMenu: (() -> Unit)? = null
}