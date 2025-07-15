package com.example.drivethrurestaurante.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.drivethrurestaurante.theme.*
import com.example.drivethrurestaurante.ui.navigation.Routes

// Modelo de datos para los items del menú
data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int? = null, // Por ahora null, luego puedes agregar imágenes
    val category: String
)

// Modelo para las categorías
data class Category(
    val name: String,
    val displayName: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val categories = listOf(
        Category("desayunos", "Desayunos"),
        Category("comidas", "Comidas"),
        Category("bebidas", "Bebidas"),
        Category("postres", "Postres")
    )

    var selectedCategory by remember { mutableStateOf("desayunos") }

    val menuItems = listOf(
        MenuItem(1, "Pancakes", "Con mantequilla, miel, frutas o chocolate.", category = "desayunos"),
        MenuItem(2, "Sándwich", "Con huevo, jamón, tocino o queso, en pan de caja.", category = "desayunos"),
        MenuItem(3, "Pancakes", "Con mantequilla, miel, frutas o chocolate.", category = "desayunos"),
        MenuItem(4, "Sándwich", "Con huevo, jamón, tocino o queso, en pan de caja.", category = "desayunos"),
        MenuItem(5, "Pancakes", "Con mantequilla, miel, frutas o chocolate.", category = "desayunos"),
        // Puedes agregar más items para otras categorías
    )

    val filteredItems = menuItems.filter { it.category == selectedCategory }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Fast Burgers",
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                },
                actions = {
                    IconButton(onClick = { /* Acción de búsqueda */ }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = TextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceLight
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Background)
        ) {
            // Navegación por pestañas
            TabRow(
                selectedTabIndex = categories.indexOfFirst { it.name == selectedCategory },
                containerColor = SurfaceLight,
                contentColor = TextPrimary,
                indicator = { tabPositions ->
                    if (tabPositions.isNotEmpty()) {
                        TabRowDefaults.Indicator(
                            modifier = Modifier.tabIndicatorOffset(
                                tabPositions[categories.indexOfFirst { it.name == selectedCategory }]
                            ),
                            color = RedPrimary,
                            height = 3.dp
                        )
                    }
                }
            ) {
                categories.forEachIndexed { index, category ->
                    Tab(
                        selected = selectedCategory == category.name,
                        onClick = { selectedCategory = category.name },
                        text = {
                            Text(
                                category.displayName,
                                fontWeight = if (selectedCategory == category.name) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedCategory == category.name) RedPrimary else TextSecondary
                            )
                        }
                    )
                }
            }

            // Contenido del menú
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Título de la sección
                item {
                    Text(
                        text = "Comidas y Snacks",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // Grid de items del menú (2 columnas)
                items(filteredItems.chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowItems.forEach { item ->
                            MenuItemCard(
                                item = item,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    navController.navigate(Routes.MENU)
                                }
                            )
                        }

                        // Si solo hay un item en la fila, agregar spacer
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MenuItemCard(
    item: MenuItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = SurfaceLight
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Círculo con imagen del producto
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(RedPrimary),
                contentAlignment = Alignment.Center
            ) {
                // Aquí puedes agregar la imagen real cuando la tengas
                // Por ahora mostramos un placeholder
                Text(
                    text = item.name.take(1),
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Nombre del producto
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Descripción
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }
    }
}