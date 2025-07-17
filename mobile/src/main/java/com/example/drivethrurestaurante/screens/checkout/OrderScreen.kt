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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.drivethrurestaurante.data.model.MenuItem
import com.example.drivethrurestaurante.theme.*
import com.example.drivethrurestaurante.ui.navigation.Routes

// Data class para representar un item en el carrito
data class CartItem(
    val menuItem: MenuItem,
    val quantity: Int = 1
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navController: NavController) {
    // Estado del carrito - en una app real esto vendría de un ViewModel
    var cartItems by remember { mutableStateOf(listOf(
        CartItem(
            MenuItem(
                id = 23,
                name = "Pancakes Clasicos",
                description = "Con mantequilla, miel, frutas o chocolate",
                category = "COMIDAS",
                price = 85.0,
                imageUrl = "https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img,w_1080,h_675/https:/www.yogurtamazonas.com/wp-content/uploads/2025/01/PANCAKES-SIN-FONDO.png"
            ),
            quantity = 1
        )
    )) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart, // <-- Cambia ShoppingBag por ShoppingCart
                            contentDescription = "Carrito",
                            tint = TextPrimary
                        )
                        Text(
                            text = "Mi pedido",
                            style = MaterialTheme.typography.titleLarge,
                            color = TextPrimary
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            cartItems = emptyList()
                        }
                    ) {
                        Text(
                            text = "Limpiar",
                            color = Error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Sección de Productos
            item {
                Text(
                    text = "Productos",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            // Lista de productos
            items(cartItems) { cartItem ->
                ProductCard(
                    cartItem = cartItem,
                    onQuantityChange = { newQuantity ->
                        cartItems = cartItems.map { item ->
                            if (item.menuItem.id == cartItem.menuItem.id) {
                                item.copy(quantity = newQuantity)
                            } else {
                                item
                            }
                        }
                    },
                    onRemove = {
                        cartItems = cartItems.flatMap { item ->
                            if (item.menuItem.id == cartItem.menuItem.id) {
                                if (item.quantity > 1) listOf(item.copy(quantity = item.quantity - 1)) else emptyList()
                            } else listOf(item)
                        }
                    },
                    onEdit = {
                        navController.navigate(Routes.MENU)
                    }
                )
            }

            // Línea divisoria
            item {
                Divider(
                    color = DividerColor,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Sección del Total
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = "$${cartItems.sumOf { it.menuItem.price * it.quantity }}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
            }

            // Espacio para los botones
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        // Botones de acción en la parte inferior
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Botón Continuar Comprando
                OutlinedButton(
                    onClick = {
                        navController.navigate(Routes.MENU)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = TextPrimary
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
                ) {
                    Text(
                        text = "Continuar Comprando",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                // Botón Confirmar Pedido
                Button(
                    onClick = {
                        navController.navigate(Routes.CONFIRMATION)
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Confirmar Pedido",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }
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
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Imagen del producto
            AsyncImage(
                model = cartItem.menuItem.imageUrl,
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
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
                Text(
                    text = cartItem.menuItem.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
                OutlinedButton(
                    onClick = onEdit,
                    modifier = Modifier.wrapContentWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFFE3F2FD),
                        contentColor = Color(0xFF1976D2)
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1976D2))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Editar",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Editar")
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
                    color = TextPrimary
                )
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(Color(0xFFF8FAFA), RoundedCornerShape(20.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onRemove() },
                        tint = IconGray
                    )
                    Text(
                        text = cartItem.quantity.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onQuantityChange(cartItem.quantity + 1) },
                        tint = IconGray
                    )
                }
            }
        }
    }
} 