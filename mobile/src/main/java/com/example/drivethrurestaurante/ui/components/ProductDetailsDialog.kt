// ProductDetailsDialog.kt
package com.example.drivethrurestaurante.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.drivethrurestaurante.ui.navigation.Routes
import com.example.drivethrurestaurante.data.model.MenuItem
import com.example.drivethrurestaurante.data.model.CartViewModel
import android.widget.Toast

@Composable
fun ProductDetailsDialog(
    menuItem: MenuItem?,
    cartViewModel: CartViewModel,
    navController: NavController,
    onDismiss: () -> Unit
) {
    if (menuItem != null) {
        // Estado local para manejar la cantidad y las instrucciones
        var localQuantity by remember { mutableStateOf(1) }
        var specialInstructions by remember { mutableStateOf("") }
        val context = LocalContext.current

        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Botón X en la esquina superior derecha
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar",
                            tint = Color(0xFF666666),
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Título
                        Text(
                            text = menuItem.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF333333),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Imagen del producto
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(menuItem.imageRes)
                                .crossfade(true)
                                .build(),
                            contentDescription = menuItem.name,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Descripción del producto
                        Text(
                            text = menuItem.description,
                            fontSize = 14.sp,
                            color = Color(0xFF666666),
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Precio
                        Text(
                            text = "$${menuItem.price}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE57373),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Controles de cantidad
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Cantidad:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF333333)
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            // Botón menos (solo visible cuando cantidad > 1)
                            if (localQuantity > 1) {
                                IconButton(
                                    onClick = { localQuantity-- },
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(
                                            Color(0xFFF5F5F5),
                                            CircleShape
                                        )
                                ) {
                                    Text(
                                        text = "-",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF333333)
                                    )
                                }
                            } else {
                                // Espacio vacío para mantener el layout cuando no hay botón menos
                                Spacer(modifier = Modifier.size(40.dp))
                            }

                            // Cantidad actual
                            Text(
                                text = localQuantity.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF333333),
                                modifier = Modifier.padding(horizontal = 20.dp)
                            )

                            // Botón más
                            IconButton(
                                onClick = { localQuantity++ },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        Color(0xFFE57373),
                                        CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Agregar",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Campo de instrucciones especiales
                        OutlinedTextField(
                            value = specialInstructions,
                            onValueChange = { specialInstructions = it },
                            label = {
                                Text(
                                    text = "Instrucciones especiales (opcional)",
                                    color = Color(0xFF666666)
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE57373),
                                unfocusedBorderColor = Color(0xFFE0E0E0)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            maxLines = 3
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Subtotal
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Subtotal:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF333333)
                            )
                            Text(
                                text = "$${String.format("%.2f", menuItem.price * localQuantity)}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFE57373)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Botón Agregar al Carrito
                        Button(
                            onClick = {
                                cartViewModel.addToCart(menuItem, localQuantity, specialInstructions, showConfirmationDialog = false)
                                Toast.makeText(context, "¡${menuItem.name} agregado al carrito!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE57373)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Agregar al Carrito",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}