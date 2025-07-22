// MenuScreen.kt - Actualizada
package com.example.drivethrurestaurante.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.drivethrurestaurante.ui.navigation.Routes
import com.example.drivethrurestaurante.data.model.MenuData
import com.example.drivethrurestaurante.data.model.MenuItem
import com.example.drivethrurestaurante.data.model.CartViewModel
import com.example.drivethrurestaurante.ui.components.OrderConfirmationDialog
import com.example.drivethrurestaurante.ui.components.ProductDetailsDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    var selectedTab by remember { mutableStateOf("Menu") }
    var selectedCategory by remember { mutableStateOf("DESAYUNOS") }

    // Diálogo de confirmación de orden
    if (cartViewModel.showOrderDialog && cartViewModel.currentAddedItem != null) {
        OrderConfirmationDialog(
            cartItem = cartViewModel.currentAddedItem,
            cartViewModel = cartViewModel,
            navController = navController,
            onDismiss = { cartViewModel.dismissOrderDialog() }
        )
    }

    // Diálogo de detalles del producto
    if (cartViewModel.showProductDetailsDialog && cartViewModel.selectedMenuItem != null) {
        ProductDetailsDialog(
            menuItem = cartViewModel.selectedMenuItem,
            cartViewModel = cartViewModel,
            navController = navController,
            onDismiss = { cartViewModel.dismissProductDetailsDialog() }
        )
    }

    Scaffold(
        topBar = {
            Column {
                // Top App Bar con navegación y carrito
                CenterAlignedTopAppBar(
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(32.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TabButton(
                                text = "Menu",
                                isSelected = selectedTab == "Menu",
                                onClick = { selectedTab = "Menu" }
                            )
                            TabButton(
                                text = "Recomendaciones",
                                isSelected = selectedTab == "Recomendaciones",
                                onClick = { selectedTab = "Recomendaciones" }
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
                        IconButton(
                            onClick = { navController.navigate(Routes.ORDER) }
                        ) {
                            Box {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = "Carrito",
                                    tint = Color(0xFF333333)
                                )
                                // Badge con cantidad de items
                                if (cartViewModel.getTotalItems() > 0) {
                                    Badge(
                                        modifier = Modifier.align(Alignment.TopEnd),
                                        containerColor = Color(0xFFE57373)
                                    ) {
                                        Text(
                                            text = cartViewModel.getTotalItems().toString(),
                                            fontSize = 10.sp,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White
                    )
                )

                // Menú de categorías mejorado (solo visible en la pestaña Menu)
                if (selectedTab == "Menu") {
                    CategoryMenuBar(
                        selectedCategory = selectedCategory,
                        onCategorySelected = { selectedCategory = it }
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            when (selectedTab) {
                "Menu" -> {
                    // Sección de Snacks (siempre visible, horizontal)
                    item {
                        MenuSection(
                            title = "Snacks",
                            items = MenuData.getSnacks(),
                            isHorizontal = true,
                            onItemClick = { item ->
                                cartViewModel.showProductDetails(item)
                            }
                        )
                    }

                    // Sección de Comidas (vertical, cambia según categoría seleccionada)
                    item {
                        val comidaItems = when (selectedCategory) {
                            "DESAYUNOS" -> MenuData.getItemsByCategory("DESAYUNOS")
                            "COMIDAS" -> MenuData.getItemsByCategory("COMIDAS")
                            "BEBIDAS" -> MenuData.getItemsByCategory("BEBIDAS")
                            "POSTRES" -> MenuData.getItemsByCategory("POSTRES")
                            else -> MenuData.getItemsByCategory("COMIDAS")
                        }

                        MenuSection(
                            title = getCategoryDisplayName(selectedCategory),
                            items = comidaItems,
                            isHorizontal = false,
                            onItemClick = { item ->
                                cartViewModel.showProductDetails(item)
                            }
                        )
                    }
                }

                "Recomendaciones" -> {
                    item {
                        RecommendationsSection(
                            onItemClick = { item ->
                                cartViewModel.showProductDetails(item)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryMenuBar(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val categories = listOf(
                "DESAYUNOS" to "Desayunos",
                "COMIDAS" to "Comidas",
                "BEBIDAS" to "Bebidas",
                "POSTRES" to "Postres"
            )

            items(categories) { (categoryKey, categoryName) ->
                CategoryTab(
                    text = categoryName,
                    isSelected = selectedCategory == categoryKey,
                    onClick = { onCategorySelected(categoryKey) }
                )
            }
        }
    }
}

@Composable
fun CategoryTab(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 2.dp) 
    ) {
        TextButton(
            onClick = onClick,
            colors = ButtonDefaults.textButtonColors(
                contentColor = if (isSelected) Color(0xFFE57373) else Color(0xFF666666)
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 2.dp) 
        ) {
            Text(
                text = text,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 14.sp
            )
        }

        // Indicador de selección
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(2.dp) // Altura reducida
                    .background(
                        Color(0xFFE57373),
                        RoundedCornerShape(1.dp)
                    )
            )
        } else {
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = onClick,
            colors = ButtonDefaults.textButtonColors(
                contentColor = if (isSelected) Color(0xFFE57373) else Color(0xFF666666)
            )
        ) {
            Text(
                text = text,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(3.dp)
                    .background(
                        Color(0xFFE57373),
                        RoundedCornerShape(1.5.dp)
                    )
            )
        }
    }
}

@Composable
fun RecommendationsSection(
    onItemClick: (MenuItem) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Recomendaciones Especiales",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Aquí podrías mostrar algunos productos recomendados
        val recommendedItems = MenuData.getItemsByCategory("COMIDAS").take(3)

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(recommendedItems) { item ->
                MenuItemCard(
                    item = item,
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}

@Composable
fun MenuSection(
    title: String,
    items: List<MenuItem>,
    isHorizontal: Boolean = false,
    onItemClick: (MenuItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp) // Espacio adicional arriba
    ) {
        // Título de la sección con mejor estilo
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Línea divisoria más sutil
        HorizontalDivider(
            color = Color(0xFFE0E0E0),
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Lista de elementos
        if (items.isNotEmpty()) {
            if (isHorizontal) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(items) { item ->
                        MenuItemCard(
                            item = item,
                            onClick = { onItemClick(item) }
                        )
                    }
                }
            } else {
                MenuItemsGrid(
                    items = items,
                    onItemClick = onItemClick
                )
            }
        } else {
            EmptyStateCard()
        }
    }
}

@Composable
fun EmptyStateCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(android.R.drawable.ic_dialog_info),
                    contentDescription = null,
                    tint = Color(0xFFBBBBBB),
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "No hay elementos disponibles",
                    color = Color(0xFF999999),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun MenuItemsGrid(
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                rowItems.forEach { item ->
                    MenuItemCard(
                        item = item,
                        onClick = { onItemClick(item) },
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun MenuItemCard(
    item: MenuItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .then(
                if (modifier == Modifier) Modifier.width(160.dp) else Modifier
            )
            .height(240.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Imagen circular del producto con fondo rojo
            Box(
                modifier = Modifier
                    .size(90.dp) 
                    .background(
                        Color(0xFFE45656),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.imageRes)
                        .crossfade(true)
                        .build(),
                    contentDescription = item.name,
                    modifier = Modifier
                        .size(70.dp) 
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(android.R.drawable.ic_menu_gallery),
                    error = painterResource(android.R.drawable.ic_menu_close_clear_cancel)
                )
            }

            // Contenido central
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp) 
            ) {
                // Nombre del producto
                Text(
                    text = item.name,
                    fontSize = 15.sp, 
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    lineHeight = 20.sp
                )

                // Descripción del producto
                Text(
                    text = item.description,
                    fontSize = 12.sp, 
                    color = Color(0xFF666666),
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp,
                    maxLines = 3 
                )
            }

            // Precio del producto
            Text(
                text = "$${item.price}",
                fontSize = 18.sp, 
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE57373)
            )
        }
    }
}

fun getCategoryDisplayName(category: String): String {
    return when (category) {
        "DESAYUNOS" -> "Desayunos"
        "COMIDAS" -> "Comidas"
        "BEBIDAS" -> "Bebidas"
        "POSTRES" -> "Postres"
        else -> "Comidas"
    }
}