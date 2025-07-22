package com.example.drivethrurestaurante.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.drivethrurestaurante.ui.navigation.Routes
import com.example.drivethrurestaurante.R
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.offset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavController,
    itemId: Int = 1
) {
    var comentarios by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf(1) }
    var showSnackbar by remember { mutableStateOf(false) }

    // Encontrar el ítem por ID
    val item = remember(itemId) {
        getAllMenuItems().find { it.id == itemId } ?: getAllMenuItems().first()
    }

    val existingCartItem = remember {
        mutableStateOf(CartState.getItems().find { it.id == itemId })
    }

    LaunchedEffect(CartState.getItems()) {
        existingCartItem.value = CartState.getItems().find { it.id == itemId }
        // Si encontramos el item en el carrito, cargar sus valores
        existingCartItem.value?.let { cartItem ->
            comentarios = cartItem.comments
            quantity = cartItem.quantity
        }
    }

    val hasChanges = remember(comentarios, quantity, existingCartItem.value) {
        existingCartItem.value != null && (
                comentarios != existingCartItem.value?.comments ||
                        quantity != existingCartItem.value?.quantity
                )
    }

    val buttonText = if (existingCartItem.value != null) {
        if (hasChanges) "Actualizar orden" else "Añadir a la orden"
    } else {
        "Añadir a la orden"
    }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar(
                message = if (existingCartItem.value != null) "Orden actualizada" else "Producto agregado a la orden",
                duration = SnackbarDuration.Short
            )
            showSnackbar = false
        }
    }

    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 4.dp,
                color = Color.White
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = "Detalles",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Regresar",
                                tint = Color.Black
                            )
                        }
                    },
                    actions = {
                        Box {
                            IconButton(onClick = { navController.navigate(Routes.createOrderSummaryRoute(item.id)) }) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = "Ver pedido",
                                    tint = Color.Black
                                )
                            }
                            // Badge con contador
                            if (CartState.getTotalItems() > 0) {
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .background(Color.Red, CircleShape)
                                        .align(Alignment.TopEnd),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = CartState.getTotalItems().toString(),
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    )
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Columna izquierda
            Column(
                modifier = Modifier.weight(0.4f)
            ) {
                if (item.imageRes != null) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.LightGray)
                    )
                }
            }

            // Columna derecha
            Column(
                modifier = Modifier.weight(0.6f)
            ) {
                Text(
                    text = "${item.name} $${item.price}",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = item.detailedDescription,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Cantidad",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        TextButton(
                            onClick = { if (quantity > 1) quantity-- },
                            modifier = Modifier.size(20.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = "-",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = quantity.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        TextButton(
                            onClick = { quantity++ },
                            modifier = Modifier.size(20.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = "+",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Comentarios o instrucciones especiales",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                OutlinedTextField(
                    value = comentarios,
                    onValueChange = { comentarios = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    placeholder = {
                        Text(
                            "Escribe aquí tus comentarios o instrucciones especiales para tu orden...",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Color(0xFFE57373)
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = {
                            val cartItem = CartItem(
                                id = item.id,
                                name = item.name,
                                price = item.price,
                                quantity = quantity,
                                comments = comentarios
                            )
                            if (existingCartItem.value != null) {
                                CartState.updateItemQuantity(item.id, quantity)
                                CartState.updateItemComments(item.id, comentarios)
                            } else {
                                CartState.addItem(cartItem)
                            }
                            showSnackbar = true
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Black
                        ),
                        border = ButtonDefaults.outlinedButtonBorder(enabled = true),
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(buttonText)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            val cartItem = CartItem(
                                id = item.id,
                                name = item.name,
                                price = item.price,
                                quantity = quantity,
                                comments = comentarios
                            )
                            if (existingCartItem.value != null) {
                                CartState.updateItemQuantity(item.id, quantity)
                                CartState.updateItemComments(item.id, comentarios)
                            } else {
                                CartState.addItem(cartItem)
                            }
                            showSnackbar = true
                            navController.navigate(Routes.createOrderSummaryRoute(item.id))
                        },
                        modifier = Modifier.width(200.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        )
                    ) {
                        Text("Terminar y pagar")
                    }
                }
            }
        }
    }
}