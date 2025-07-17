package com.example.drivethrurestaurante.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.drivethrurestaurante.ui.navigation.Routes
import kotlinx.coroutines.delay
import com.example.drivethrurestaurante.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCarousel(
    images: List<String>,
    modifier: Modifier = Modifier,
    autoScrollDelay: Long = 2000L
) {
    val listState = rememberLazyListState()
    var currentIndex by remember { mutableStateOf(0) }
    var isUserInteracting by remember { mutableStateOf(false) }

    // Detectar cuando el usuario está interactuando con el carrusel
    LaunchedEffect(listState.isScrollInProgress) {
        if (listState.isScrollInProgress) {
            isUserInteracting = true
        } else {
            // Pequeño delay para evitar que se reactive inmediatamente
            delay(500)
            isUserInteracting = false
        }
    }

    // Sincronizar el índice actual con la posición del scroll
    LaunchedEffect(listState.firstVisibleItemIndex) {
        currentIndex = listState.firstVisibleItemIndex
    }

    // Auto-scroll effect (solo cuando el usuario no está interactuando)
    LaunchedEffect(currentIndex, isUserInteracting) {
        if (!isUserInteracting) {
            delay(autoScrollDelay)
            val nextIndex = (currentIndex + 1) % images.size
            listState.animateScrollToItem(nextIndex)
            currentIndex = nextIndex
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Carousel
        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(images) { index, imageUrl ->
                Card(
                    modifier = Modifier
                        .width(350.dp)
                        .height(200.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 12.dp,
                        pressedElevation = 16.dp
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = imageUrl)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                        size(Size.ORIGINAL)
                                    }).build()
                            ),
                            contentDescription = "Imagen promocional ${index + 1}",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        // Overlay gradient for better text visibility if needed
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    androidx.compose.ui.graphics.Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.1f)
                                        )
                                    )
                                )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Dots indicator with improved styling
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(images.size) { index ->
                Box(
                    modifier = Modifier
                        .size(if (index == currentIndex) 14.dp else 10.dp)
                        .clip(CircleShape)
                        .background(
                            if (index == currentIndex)
                                MaterialTheme.colorScheme.primary
                            else
                                Color.Gray.copy(alpha = 0.3f)
                        )
                        .clickable {
                            currentIndex = index
                            // Marcar como interacción del usuario temporalmente
                            isUserInteracting = true
                        }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val promotionalImages = listOf(
        "https://img.freepik.com/fotos-premium/hamburguesa-jugosa-carne-res-parrilla-hamburguesa-hamburguesa-cerca-hamburguesa-queso-fritas-bebida-copyspace_1135385-21932.jpg",
        "https://fiverr-res.cloudinary.com/images/t_main1,q_auto,f_auto,q_auto,f_auto/gigs/287471278/original/956e5560ffc0dd48c3563975fdec7407a0ed9e8e/do-poster-banner-business-card-flyer-design-with-my-best-effort.jpg",
        "https://img.freepik.com/vector-premium/plantilla-banner-comida-rapida-promociones-plantilla-diseno-redes-sociales-web_553310-695.jpg?w=2000"
    )

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 12.dp,
                tonalElevation = 0.dp
            ) {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Logo
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Fast Burgers Logo",
                                modifier = Modifier.size(32.dp)
                            )

                            // Texto del título
                            Text(
                                "Fast Burgers",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hero section with logo and welcome
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                                Color.Transparent
                            )
                        )
                    )
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo central
                Image(
                    painter = painterResource(id = R.drawable.logo1),
                    contentDescription = "Logo Fast Burguers",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Welcome message
                Text(
                    text = "¡Bienvenido!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Drive, order, bite!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Image carousel
            ImageCarousel(
                images = promotionalImages,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Content section with card and button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // Value proposition card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        width = 1.dp,
                        color = Color.Gray.copy(alpha = 0.15f)
                    ),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Speed icon
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "⚡",
                                fontSize = 24.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Main descriptive text
                        Text(
                            text = "Ordena rápido y sin bajarte del coche",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 20.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Call to action button
                Button(
                    onClick = {
                        navController.navigate(Routes.MENU)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 12.dp
                    )
                ) {
                    Text(
                        text = "Comenzar pedido",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Secondary action
                TextButton(
                    onClick = {
                        navController.navigate(Routes.MENU)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Ver menú completo",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}