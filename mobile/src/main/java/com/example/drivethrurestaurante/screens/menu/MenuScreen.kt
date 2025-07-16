package com.example.drivethrurestaurante.screens.menu

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import com.example.drivethrurestaurante.R

// Data classes para los elementos del menú
data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val category: String
)

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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Menu") }

    // Datos de ejemplo 
    val menuItems = listOf(
        MenuItem(1, "Pancakes", "Con mantequilla, miel, frutas o chocolate.", "snacks"),
        MenuItem(2, "Sándwich", "Con huevo, jamón, tocino o queso, en pan de caja", "snacks"),
        MenuItem(3, "Pancakes", "Con mantequilla, miel, frutas o chocolate.", "snacks"),
        MenuItem(4, "Sándwich", "Con huevo, jamón, tocino o queso, en pan de caja", "snacks"),
        MenuItem(5, "Pancakes", "Con mantequilla, miel, frutas o chocolate.", "snacks")
    )

    Column {
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
                Image(
                    painter = painterResource(id = R.drawable.logo2),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )
            },
            actions = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    TopBarButton("Desayunos")
                    TopBarButton("Comidas")
                    TopBarButton("Bebidas")
                    TopBarButton("Postres")
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            )
        )
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(2.dp)
                    .background(Color(0xFFE57373))
                    .align(if (selectedTab == "Menu") Alignment.CenterStart else Alignment.Center)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Sección Comidas y Snacks
            item {
                MenuSection(
                    title = "Comidas y Snacks",
                    items = menuItems,
                    onItemClick = { item ->
                        navController.navigate(Routes.ORDER)
                    }
                )
            }

            // Sección Ensaladas
            item {
                MenuSection(
                    title = "Ensaladas",
                    items = emptyList(), 
                    onItemClick = { item ->
                        navController.navigate(Routes.ORDER)
                    }
                )
            }
        }
    }
}

@Composable
fun TopBarButton(text: String) {
    TextButton(
        onClick = { /* Filtrar por categoría */ },
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.Gray
        )
    ) {
        Text(
            text = text,
            fontSize = 12.sp
        )
    }
}

@Composable
fun MenuSection(
    title: String,
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        if (items.isNotEmpty()) {
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Próximamente...",
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
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(140.dp)
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
        }
    }
}