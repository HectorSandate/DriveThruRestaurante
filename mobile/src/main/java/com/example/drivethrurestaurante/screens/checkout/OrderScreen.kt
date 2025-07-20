// OrderScreen.kt
package com.example.drivethrurestaurante.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.drivethrurestaurante.ui.navigation.Routes
import com.example.drivethrurestaurante.data.model.CartViewModel
import com.example.drivethrurestaurante.data.model.CartItem
import com.example.drivethrurestaurante.ui.components.OrderConfirmationDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    // Mostrar diálogo de edición si está activo
    if (cartViewModel.showOrderDialog && cartViewModel.currentAddedItem != null) {
        OrderConfirmationDialog(
            cartItem = cartViewModel.currentAddedItem,
            cartViewModel = cartViewModel,
            navController = navController,
            onDismiss = { cartViewModel.dismissOrderDialog() }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Carrito",
                            tint = Color(0xFF333333)
                        )
                        Text(
                            text = "Mi pedido",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF333333)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color(0xFF333333)
                        )
                    }
                },
                actions = {
                    if (cartViewModel.cartItems.isNotEmpty()) {
                        TextButton(
                            onClick = {
                                cartViewModel.clearCart()
                            }
                        ) {
                            Text(
                                text = "Limpiar",
                                color = Color(0xFFE57373),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        if (cartViewModel.cartItems.isEmpty()) {
            // Vista cuando el carrito está vacío
            EmptyCartView(
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        } else {
            // Vista con productos en el carrito
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Lista de productos scrolleable
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // Sección de Productos
                    item {
                        Text(
                            text = "Productos (${cartViewModel.getTotalItems()} items)",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF333333)
                        )
                    }

                    // Lista de productos
                    items(cartViewModel.cartItems) { cartItem ->
                        ProductCard(
                            cartItem = cartItem,
                            onQuantityChange = { newQuantity ->
                                cartViewModel.updateQuantity(cartItem.menuItem.id, newQuantity)
                            },
                            onRemove = {
                                cartViewModel.removeFromCart(cartItem.menuItem.id)
                            },
                            onEdit = {
                                // Configurar el item actual para editar y mostrar diálogo
                                cartViewModel.currentAddedItem = cartItem
                                cartViewModel.showOrderDialog = true
                            }
                        )
                    }

                    // Línea divisoria
                    item {
                        HorizontalDivider(
                            color = Color(0xFFE0E0E0),
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }

                    // Sección del Total
                    item {
                        TotalSection(total = cartViewModel.getTotal())
                    }

                    // Espacio adicional para evitar que los botones cubran contenido
                    item {
                        Spacer(modifier = Modifier.height(140.dp))
                    }
                }

                // Botones de acción fijos en la parte inferior
                BottomActionButtons(
                    navController = navController,
                    onContinueShopping = {
                        navController.navigate(Routes.MENU)
                    },
                    onConfirmOrder = {
                        navController.navigate(Routes.CONFIRMATION)
                    }
                )
            }
        }
    }
}

@Composable
fun EmptyCartView(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Carrito vacío",
            modifier = Modifier.size(80.dp),
            tint = Color(0xFFBBBBBB)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tu carrito está vacío",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Agrega algunos productos deliciosos a tu pedido",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF666666),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate(Routes.MENU)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE57373)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Explorar Menú",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ProductCard(
    cartItem: CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit,
    onEdit: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Imagen del producto
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(cartItem.menuItem.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = cartItem.menuItem.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                // Información del producto
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = cartItem.menuItem.name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                    Text(
                        text = cartItem.menuItem.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF666666)
                    )

                    // Mostrar instrucciones especiales si existen
                    if (cartItem.specialInstructions.isNotEmpty()) {
                        Text(
                            text = "Notas: ${cartItem.specialInstructions}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFE57373),
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Botón editar
                    TextButton(
                        onClick = onEdit,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color(0xFFE57373)
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Editar", fontSize = 14.sp)
                    }
                }

                // Precio y controles de cantidad
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "$${cartItem.menuItem.price}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )

                    // Controles de cantidad
                    Row(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color(0xFFE0E0E0),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .background(Color(0xFFF8F8F8), RoundedCornerShape(20.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                if (cartItem.quantity > 1) {
                                    onQuantityChange(cartItem.quantity - 1)
                                } else {
                                    onRemove()
                                }
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = if (cartItem.quantity > 1) Icons.Default.Clear else Icons.Default.Delete,
                                contentDescription = if (cartItem.quantity > 1) "Reducir cantidad" else "Eliminar",
                                modifier = Modifier.size(16.dp),
                                tint = Color(0xFF666666)
                            )
                        }

                        Text(
                            text = cartItem.quantity.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF333333)
                        )

                        IconButton(
                            onClick = { onQuantityChange(cartItem.quantity + 1) },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Aumentar cantidad",
                                modifier = Modifier.size(16.dp),
                                tint = Color(0xFF666666)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TotalSection(total: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F8F8)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
            Text(
                text = "$${String.format("%.2f", total)}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE57373),
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun BottomActionButtons(
    navController: NavController,
    onContinueShopping: () -> Unit,
    onConfirmOrder: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Botón Continuar Comprando
            OutlinedButton(
                onClick = onContinueShopping,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF333333)
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E0E0)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Continuar Comprando",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Botón Confirmar Pedido
            Button(
                onClick = onConfirmOrder,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE57373)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Confirmar Pedido",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }
    }
}