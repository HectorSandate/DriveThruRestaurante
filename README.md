# 📲🚗 DriveThru Restaurante

Este proyecto consiste en dos aplicaciones (una **móvil** y otra **automotive**) que se comunican a través de un servidor WebSocket para facilitar pedidos tipo drive-thru de forma remota.

---

## 🔌 Conexión entre la app móvil y la automotriz

Para establecer correctamente la comunicación entre ambas apps:

### 1. Ejecutar el servidor

Primero debes iniciar el servidor WebSocket:

- Ve al módulo **`servidor`**
- Ejecuta el archivo `Servidor.kt` desde Android Studio o IntelliJ:
  Run → Servidor.kt

---

### 2. Configurar la IP en cada app

La IP del servidor **debe coincidir** con la red en la que estás ejecutando las apps. Dependiendo de dónde se ejecuten (emulador o dispositivo físico), debes ajustar lo siguiente en **ambas apps**:

#### Cambiar IP en:
- `MainActivity.kt`
- `network_security_config.xml`

#### ✅ Si usas emulador:
- Usa la IP:
  10.0.2.2

#### ✅ Si usas dispositivo físico:
- Usa la IP real local de tu computadora (ej. `192.168.0.105`)
- Puedes encontrarla con `ipconfig` (Windows) o `ifconfig` (Mac/Linux)

---

### 3. Ejecutar las apps

- Inicia la app **móvil** y la app **automotive** (en emulador o dispositivo físico).
- En la app **móvil**, escanea el código QR.
- La app **automotive** recibirá la información del QR y abrirá el menú automáticamente.

---