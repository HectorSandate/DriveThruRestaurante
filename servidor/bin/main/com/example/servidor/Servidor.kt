import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration
import java.util.Collections
import java.util.LinkedHashSet

// Lista sincronizada de clientes conectados
val clientesConectados = mutableSetOf<DefaultWebSocketServerSession?>()

// Mapa para rastrear qué tipo de cliente es cada sesión
val tipoCliente = mutableMapOf<DefaultWebSocketServerSession, String>()

// Función para reenviar mensajes del automotive al móvil
suspend fun reenviarAMovil(mensaje: String, nombreCliente: String) {
    val clientesMovil = clientesConectados.filter { tipoCliente[it] == "MOVIL" }
    
    if (clientesMovil.isEmpty()) {
        println("⚠️ No hay clientes móvil conectados para recibir el carrito")
        return
    }
    
    clientesMovil.forEach { cliente ->
        try {
            cliente?.send(mensaje)
            println("✅ Carrito reenviado de $nombreCliente a MOVIL")
        } catch (e: Exception) {
            println("❌ Error reenviando carrito a móvil: ${e.message}")
        }
    }
}

// Función para reenviar mensajes del móvil al automotive
suspend fun reenviarAAutomotive(mensaje: String, nombreCliente: String) {
    val clientesAutomotive = clientesConectados.filter { tipoCliente[it] == "AUTOMOTIVE" }
    
    if (clientesAutomotive.isEmpty()) {
        println("⚠️ No hay clientes automotive conectados para recibir el carrito")
        return
    }
    
    clientesAutomotive.forEach { cliente ->
        try {
            cliente?.send(mensaje)
            println("✅ Carrito reenviado de $nombreCliente a AUTOMOTIVE")
        } catch (e: Exception) {
            println("❌ Error reenviando carrito a automotive: ${e.message}")
        }
    }
}

// Función para reenviar mensajes generales a todos
suspend fun reenviarATodos(mensaje: String, nombreCliente: String) {
    val otrosClientes = clientesConectados.filter { it != null }
    
    if (otrosClientes.isEmpty()) {
        println("⚠️ No hay otros clientes conectados para reenviar el mensaje")
        return
    }
    
    otrosClientes.forEach { cliente ->
        try {
            cliente?.send("De $nombreCliente: $mensaje")
            println("✅ Mensaje general reenviado de $nombreCliente")
        } catch (e: Exception) {
            println("❌ Error reenviando mensaje general: ${e.message}")
        }
    }
}

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(WebSockets) {
            pingPeriod = Duration.ofSeconds(15)
        }

        routing {
            webSocket("/chat") {
                println("Cliente conectado!")
                clientesConectados += this

                var nombreCliente = "Desconocido"
                var esPrimerMensaje = true

                try {
                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            val recibido = frame.readText()

                            if (esPrimerMensaje) {
                                nombreCliente = recibido
                                tipoCliente[this] = nombreCliente
                                esPrimerMensaje = false
                                println("✅ $nombreCliente se identificó")
                            } else {
                                println("📨 Mensaje de $nombreCliente: $recibido")

                                // Determinar el tipo de mensaje y reenviar apropiadamente
                                when {
                                    recibido.startsWith("De AUTOMOTIVE:") -> {
                                        // Mensaje de carrito del automotive, enviar solo al móvil
                                        reenviarAMovil(recibido, nombreCliente)
                                    }
                                    recibido.startsWith("De MOVIL:") -> {
                                        // Mensaje de carrito del móvil, enviar solo al automotive
                                        reenviarAAutomotive(recibido, nombreCliente)
                                    }
                                    else -> {
                                        // Mensaje general, reenviar a todos los demás
                                        reenviarATodos(recibido, nombreCliente)
                                    }
                                }
                            }

                            send("Hola $nombreCliente, recibí tu mensaje: $recibido")
                        }
                    }
                } catch (e: Exception) {
                    println("❌ Error en cliente $nombreCliente: ${e.message}")
                } finally {
                    println("$nombreCliente se desconectó.")
                    clientesConectados -= this
                    tipoCliente.remove(this)
                }
            }
        }
    }.start(wait = true)
}
