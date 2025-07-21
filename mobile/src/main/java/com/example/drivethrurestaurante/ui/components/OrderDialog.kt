// OrderConfirmationDialog.kt - Mejorado
package com.example.drivethrurestaurante.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
import com.example.drivethrurestaurante.data.model.CartItem
import com.example.drivethrurestaurante.data.model.CartViewModel

@Composable
fun OrderConfirmationDialog(
    cartItem: CartItem?,
    cartViewModel: CartViewModel,
    navController: NavController,
    onDismiss: () -> Unit
) {
    if (cartItem != null) {
        // Estado local para manejar la cantidad y las instrucciones
        var localQuantity by remember(cartItem) { mutableStateOf(cartItem.quantity) }
        var specialInstructions by remember(cartItem) { mutableStateOf(cartItem.specialInstructions) }

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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Título
                    Text(
                        text = "¡Agregado al carrito!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Imagen del producto
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(cartItem.menuItem.imageRes)
                            .crossfade(true)
                            .build(),
                        contentDescription = cartItem.menuItem.name,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Información del producto
                    Text(
                        text = cartItem.menuItem.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "$${cartItem.menuItem.price}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFE57373),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Controles de cantidad mejorados
                    Row(
                        modifier = Modifier
                            .background(
                                Color(0xFFF5F5F5),
                                RoundedCornerShape(25.dp)
                            )
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                if (localQuantity > 1) {
                                    localQuantity--
                                }
                            },
                            enabled = localQuantity > 1
                        ) {
                        }

                        Text(
                            text = localQuantity.toString(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color(0xFF333333)
                        )

                        IconButton(
                            onClick = {
                                localQuantity++
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Aumentar cantidad",
                                tint = Color(0xFF666666)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Campo para instrucciones especiales
                    OutlinedTextField(
                        value = specialInstructions,
                        onValueChange = { specialInstructions = it },
                        label = { Text("Instrucciones especiales (opcional)") },
                        placeholder = { Text("Ej: Sin cebolla, extra queso...") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFE57373),
                            focusedLabelColor = Color(0xFFE57373),
                            cursorColor = Color(0xFFE57373)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Mostrar precio total
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF8F8F8)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Subtotal:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF666666)
                            )
                            Text(
                                text = "$${String.format("%.2f", cartItem.menuItem.price * localQuantity)}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFE57373)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Botones
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Botón Ver Carrito
                        Button(
                            onClick = {
                                // Actualizar el item en el carrito con los cambios locales
                                cartViewModel.updateQuantity(cartItem.menuItem.id, localQuantity)
                                cartViewModel.updateSpecialInstructions(cartItem.menuItem.id, specialInstructions)

                                onDismiss()
                                navController.navigate(Routes.ORDER)
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
                                text = "Ver Carrito (${cartViewModel.getTotalItems() + (localQuantity - cartItem.quantity)})",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }

                        // Botón Seguir Agregando
                        OutlinedButton(
                            onClick = {
                                // Actualizar el item en el carrito con los cambios locales
                                cartViewModel.updateQuantity(cartItem.menuItem.id, localQuantity)
                                cartViewModel.updateSpecialInstructions(cartItem.menuItem.id, specialInstructions)

                                onDismiss()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color(0xFFE57373)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Seguir Agregando",
                                fontSize = 16.sp
                            )
                        }

                        // Botón Eliminar (solo si ya existe en el carrito)
                        if (cartItem.quantity > 0) {
                            TextButton(
                                onClick = {
                                    cartViewModel.removeFromCart(cartItem.menuItem.id)
                                    onDismiss()
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar del carrito",
                                    modifier = Modifier.size(16.dp),
                                    tint = Color(0xFF999999)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Eliminar del carrito",
                                    color = Color(0xFF999999),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}