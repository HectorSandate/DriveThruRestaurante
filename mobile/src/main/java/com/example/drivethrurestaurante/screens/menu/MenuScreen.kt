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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.drivethrurestaurante.ui.navigation.Routes
import com.example.drivethrurestaurante.data.model.MenuData
import com.example.drivethrurestaurante.data.model.MenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Menu") }
    var selectedCategory by remember { mutableStateOf("DESAYUNOS") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
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
                            contentDescription = "Volver"
                        )
                    }
                },
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        TopBarButton(
                            text = "Desayunos",
                            isSelected = selectedCategory == "DESAYUNOS",
                            onClick = { selectedCategory = "DESAYUNOS" }
                        )
                        TopBarButton(
                            text = "Comidas",
                            isSelected = selectedCategory == "COMIDAS",
                            onClick = { selectedCategory = "COMIDAS" }
                        )
                        TopBarButton(
                            text = "Bebidas",
                            isSelected = selectedCategory == "BEBIDAS",
                            onClick = { selectedCategory = "BEBIDAS" }
                        )
                        TopBarButton(
                            text = "Postres",
                            isSelected = selectedCategory == "POSTRES",
                            onClick = { selectedCategory = "POSTRES" }
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5)),
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
                                navController.navigate(Routes.ORDER)
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
                                navController.navigate(Routes.ORDER)
                            }
                        )
                    }
                }

                "Recomendaciones" -> {
                    // Aquí puedes agregar la lógica para recomendaciones
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Recomendaciones próximamente...",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
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
                contentColor = if (isSelected) Color(0xFFE57373) else Color.Gray
            )
        ) {
            Text(
                text = text,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                fontSize = 16.sp
            )
        }

        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(2.dp)
                    .background(Color(0xFFE57373))
            )
        }
    }
}

@Composable
fun TopBarButton(
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = if (isSelected) Color(0xFFE57373) else Color.Gray
        )
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
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
    ) {
        // Título de la sección
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Línea divisoria
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Lista de elementos
        if (items.isNotEmpty()) {
            if (isHorizontal) {
                // Lista horizontal para snacks
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
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
                // Lista vertical para comidas generales
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items.chunked(2).forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            rowItems.forEach { item ->
                                MenuItemCard(
                                    item = item,
                                    onClick = { onItemClick(item) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            // Si solo hay un elemento en la fila, añade un spacer
                            if (rowItems.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        } else {
            // Placeholder para secciones vacías
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No hay elementos disponibles",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
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
                if (modifier == Modifier) Modifier.width(140.dp) else Modifier
            )
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen circular del producto (placeholder por ahora)
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE57373)),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder con texto hasta que agregues las imágenes
                Text(
                    text = item.name.take(2).uppercase(),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Nombre del producto
            Text(
                text = item.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción del producto
            Text(
                text = item.description,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Precio del producto
            Text(
                text = "$${item.price}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE57373)
            )
        }
    }
}

// Función auxiliar para obtener el nombre de display de la categoría
fun getCategoryDisplayName(category: String): String {
    return when (category) {
        "DESAYUNOS" -> "Desayunos"
        "COMIDAS" -> "Comidas"
        "BEBIDAS" -> "Bebidas"
        "POSTRES" -> "Postres"
        else -> "Comidas"
    }
}