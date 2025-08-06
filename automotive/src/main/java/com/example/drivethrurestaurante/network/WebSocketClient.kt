package com.example.drivethrurestaurante.network

import okhttp3.*

class WebSocketClient(
    private val serverUrl: String,
    private val listener: WebSocketListener
) {
    private var webSocket: WebSocket? = null

    fun connect() {
        val client = OkHttpClient()
        val request = Request.Builder().url(serverUrl).build()
        webSocket = client.newWebSocket(request, listener)
    }

    fun send(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Cerrado por el cliente")
    }
}

class MiWebSocketListener : WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response) {
        println("Conectado al servidor WebSocket ✅")
        webSocket.send("¡Hola desde el cliente!")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        println("Mensaje recibido: $text")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        println("Cerrando conexión: $reason")
        webSocket.close(1000, null)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        println("Error: ${t.message}")
    }
}
