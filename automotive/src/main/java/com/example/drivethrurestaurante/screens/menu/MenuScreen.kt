package com.example.drivethrurestaurante.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.drivethrurestaurante.ui.navigation.Routes
import androidx.compose.ui.res.painterResource
import com.example.drivethrurestaurante.R
import androidx.compose.foundation.clickable
import kotlinx.coroutines.launch

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
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
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
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Datos de ejemplo
    val desayunosItems = getAllMenuItems().filter { it.category == "desayunos" }
    val comidasItems = getAllMenuItems().filter { it.category == "comidas" }
    val platosFuertesItems = getAllMenuItems().filter { it.category == "platos_fuertes" }
    val bebidasItems = getAllMenuItems().filter { it.category == "bebidas" }
    val postresItems = getAllMenuItems().filter { it.category == "postres" }

    // Section anchors for scrolling
    var desayunosSectionIndex by remember { mutableStateOf(0) }
    var comidasSectionIndex by remember { mutableStateOf(0) }
    var platosFuertesSectionIndex by remember { mutableStateOf(0) }
    var bebidasSectionIndex by remember { mutableStateOf(0) }
    var postresSectionIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            Column {
                Surface(
                    shadowElevation = 4.dp,
                    color = Color.White
                ) {
                    CenterAlignedTopAppBar(
                        title = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.logo),
                                    contentDescription = "Logo",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(start = 16.dp)
                                )
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Box(modifier = Modifier.width(IntrinsicSize.Min)) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            TabButton(
                                                text = "Menú",
                                                isSelected = selectedTab == "Menu",
                                                onClick = { selectedTab = "Menu" }
                                            )
                                            if (selectedTab == "Menu") {
                                                Box(
                                                    modifier = Modifier
                                                        .width(50.dp)
                                                        .height(2.dp)
                                                        .background(Color(0xFFE57373))
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Box(modifier = Modifier.width(IntrinsicSize.Min)) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            TabButton(
                                                text = "Recomendaciones",
                                                isSelected = selectedTab == "Recomendaciones",
                                                onClick = { selectedTab = "Recomendaciones" }
                                            )
                                            if (selectedTab == "Recomendaciones") {
                                                Box(
                                                    modifier = Modifier
                                                        .width(120.dp)
                                                        .height(2.dp)
                                                        .background(Color(0xFFE57373))
                                                )
                                            }
                                        }
                                    }
                                }
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(end = 16.dp)
                                ) {
                                    TopBarButton(
                                        text = "Desayunos",
                                        onClick = {
                                            coroutineScope.launch {
                                                listState.animateScrollToItem(desayunosSectionIndex)
                                            }
                                        }
                                    )
                                    TopBarButton(
                                        text = "Comidas",
                                        onClick = {
                                            coroutineScope.launch {
                                                listState.animateScrollToItem(comidasSectionIndex)
                                            }
                                        }
                                    )
                                    TopBarButton(
                                        text = "Bebidas",
                                        onClick = {
                                            coroutineScope.launch {
                                                listState.animateScrollToItem(bebidasSectionIndex)
                                            }
                                        }
                                    )
                                    TopBarButton(
                                        text = "Postres",
                                        onClick = {
                                            coroutineScope.launch {
                                                listState.animateScrollToItem(postresSectionIndex)
                                            }
                                        }
                                    )
                                    // Icono de bolsa
                                    Box {
                                        IconButton(onClick = { navController.navigate(com.example.drivethrurestaurante.ui.navigation.Routes.createOrderSummaryRoute(1)) }) {
                                            Icon(
                                                imageVector = Icons.Filled.ShoppingCart,
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
                                                    fontSize = 9.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.White
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        when (selectedTab) {
            "Menu" -> {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color.White),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Sección Desayunos
                    item {
                        LaunchedEffect(Unit) {
                            desayunosSectionIndex = 0
                        }
                        Text(
                            text = "Menú / Desayunos",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                        )
                    }

                    item {
                        MenuSection(
                            title = "Comidas y Snacks",
                            items = desayunosItems,
                            onItemClick = { item ->
                                navController.navigate(Routes.createOrderRoute(item.id))
                            }
                        )
                    }

                    // Sección Comidas
                    item {
                        LaunchedEffect(Unit) {
                            comidasSectionIndex = 2
                        }
                        Text(
                            text = "Menú / Comidas",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp)
                        )
                    }

                    item {
                        MenuSection(
                            title = "Ensaladas",
                            items = comidasItems,
                            onItemClick = { item ->
                                navController.navigate(Routes.createOrderRoute(item.id))
                            }
                        )
                    }

                    // Sección Platos Fuertes
                    item {
                        LaunchedEffect(Unit) {
                            platosFuertesSectionIndex = 4
                        }
                        Text(
                            text = "Menú / Platos Fuertes",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp)
                        )
                    }

                    item {
                        MenuSection(
                            title = "Platos Fuertes",
                            items = platosFuertesItems,
                            onItemClick = { item ->
                                navController.navigate(Routes.createOrderRoute(item.id))
                            }
                        )
                    }

                    // Sección Bebidas
                    item {
                        LaunchedEffect(Unit) {
                            bebidasSectionIndex = 6
                        }
                        Text(
                            text = "Menú / Bebidas",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp)
                        )
                    }

                    item {
                        MenuSection(
                            title = "Bebidas Frias",
                            items = bebidasItems,
                            onItemClick = { item ->
                                navController.navigate(Routes.createOrderRoute(item.id))
                            }
                        )
                    }

                    // Sección Postres
                    item {
                        LaunchedEffect(Unit) {
                            postresSectionIndex = 8
                        }
                        Text(
                            text = "Menú / Postres",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp)
                        )
                    }

                    item {
                        MenuSection(
                            title = "Postres",
                            items = postresItems,
                            onItemClick = { item ->
                                navController.navigate(Routes.createOrderRoute(item.id))
                            }
                        )
                    }
                }
            }
            "Recomendaciones" -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color.White),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = "Promociones Especiales",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                        )
                    }
                    
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PromocionCard(
                                imageRes = R.drawable.promocion1,
                                title = "Combo Familiar",
                                description = "El que alcanza para todos",
                                onClick = {
                                    navController.navigate(Routes.createOrderRoute(10))
                                }
                            )

                            Spacer(modifier = Modifier.width(16.dp))
                            
                            PromocionCard(
                                imageRes = R.drawable.promocion2,
                                title = "La Promo Más Familiar",
                                description = "Llévate un refresco de 2.5 Lts.",
                                onClick = {
                                    navController.navigate(Routes.createOrderRoute(11))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopBarButton(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
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
fun PromocionCard(
    imageRes: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = title,
        modifier = modifier
            .height(450.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable(onClick = onClick),
        contentScale = ContentScale.Fit
    )
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
        // Título de la sección
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp) // Reduced from 16.dp
        )

        // Línea divisoria
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 16.dp) // Reduced from 24.dp
        )

        // Lista horizontal de elementos
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
            // Placeholder para secciones vacías
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
    Column(
        modifier = Modifier
            .width(140.dp)
            .wrapContentHeight()
            .background(Color.White)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Círculo rojo con imagen
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFE57373)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
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
            lineHeight = 16.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}