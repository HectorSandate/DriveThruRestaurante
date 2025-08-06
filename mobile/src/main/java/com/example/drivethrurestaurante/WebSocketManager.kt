package com.example.drivethrurestaurante

import okhttp3.*
import okio.ByteString
import java.util.concurrent.TimeUnit
import android.util.Log

class WebSocketManager(
    private val serverUrl: String,
    private val onConnected: () -> Unit = {},
    private val onMessageReceived: (String) -> Unit = {},
    private val onError: (Throwable) -> Unit = {}
) {
    private var webSocket: WebSocket? = null

    fun connect() {
        val client = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url(serverUrl)
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                onConnected()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d("WebSocketManager", "Mensaje recibido crudo: $text")
                onMessageReceived(text)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                val text = bytes.utf8()
                Log.d("WebSocketManager", "Mensaje recibido bytes: $text")
                onMessageReceived(text)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                onError(t)
            }
        })

        //client.dispatcher.executorService.shutdown()
    }

    fun send(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Cerrando conexión")
    }
}
