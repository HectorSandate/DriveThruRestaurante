package com.example.drivethrurestaurante.screens.menu

import com.example.drivethrurestaurante.R

// Data classes para los elementos del menú
data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val detailedDescription: String,
    val category: String,
    val imageRes: Int,
    val price: Double
)

// Función para obtener todos los ítems del menú
fun getAllMenuItems(): List<MenuItem> {
    val desayunosItems = listOf(
        MenuItem(
            id = 1,
            name = "Pancakes",
            description = "Con mantequilla, miel, frutas o chocolate.",
            detailedDescription = "Nuestros pancakes son preparados con una mezcla especial de harina, huevo, mantequilla, leche y un toque de vainilla, logrando una textura suave y esponjosa. Se sirven calientes, acompañados de mantequilla derretida y miel de maple 100% natural.",
            category = "desayunos",
            imageRes = R.drawable.pancakes,
            price = 75.0
        ),
        MenuItem(
            id = 2,
            name = "Sándwich",
            description = "Con huevo, jamón, tocino o queso, en pan de caja",
            detailedDescription = "Delicioso sándwich preparado con pan recién horneado, huevo revuelto, jamón de pavo, tocino crujiente y queso gouda derretido. Acompañado de papas a la francesa y ensalada fresca.",
            category = "desayunos",
            imageRes = R.drawable.sandwich,
            price = 65.0
        ),
        MenuItem(
            id = 3,
            name = "Hot Cakes",
            description = "Con mantequilla, miel, frutas o chocolate.",
            detailedDescription = "Hot cakes esponjosos hechos a la plancha, servidos con mantequilla de la casa, miel de maple pura y una selección de frutas frescas de temporada. Opción de agregar chocolate derretido o crema batida.",
            category = "desayunos",
            imageRes = R.drawable.pancakes,
            price = 70.0
        ),
        MenuItem(
            id = 4,
            name = "Club Sándwich",
            description = "Con pollo, jamón, tocino, queso y vegetales",
            detailedDescription = "Club Sándwich de tres pisos con pechuga de pollo a la plancha, jamón de pavo, tocino crujiente, queso cheddar, lechuga fresca, tomate y aguacate. Servido con papas fritas sazonadas y aderezo especial de la casa.",
            category = "desayunos",
            imageRes = R.drawable.sandwich,
            price = 85.0
        ),
        MenuItem(
            id = 5,
            name = "Waffles",
            description = "Con mantequilla, miel, frutas o chocolate.",
            detailedDescription = "Waffles belgas hechos al momento con masa artesanal, servidos con mantequilla de la casa, frutas frescas y miel de maple. Perfectamente dorados por fuera y suaves por dentro, con la opción de agregar crema batida o chocolate.",
            category = "desayunos",
            imageRes = R.drawable.pancakes,
            price = 80.0
        )
    )

    val comidasItems = listOf(
        MenuItem(
            id = 6,
            name = "Ensalada César",
            description = "Lechuga romana, crutones, parmesano y aderezo",
            detailedDescription = "Clásica ensalada César preparada con lechuga romana fresca, crutones hechos en casa, queso parmesano recién rallado y nuestro famoso aderezo César cremoso. Servida con pechuga de pollo a la parrilla opcional.",
            category = "comidas",
            imageRes = R.drawable.ensalada_cesar,
            price = 95.0
        ),
        MenuItem(
            id = 7,
            name = "Ensalada de Pollo Asado",
            description = "Con espinacas, manzana, nuez y vinagreta balsámica",
            detailedDescription = "Ensalada fresca y nutritiva con espinacas baby, pechuga de pollo asada y marinada, manzanas verdes crujientes, nueces caramelizadas, arándanos secos y queso de cabra, aderezada con nuestra vinagreta balsámica casera.",
            category = "comidas",
            imageRes = R.drawable.ensalada_pollo,
            price = 110.0
        ),
        MenuItem(
            id = 8,
            name = "Sopa del Día",
            description = "Pregunta por nuestra opción casera del día",
            detailedDescription = "Sopa preparada diariamente con ingredientes frescos y de temporada. Servida con crutones caseros y hierbas frescas. Perfecta para comenzar tu comida o como plato ligero y reconfortante.",
            category = "comidas",
            imageRes = R.drawable.sopa_dia,
            price = 55.0
        ),
        MenuItem(
            id = 9,
            name = "Guacamole con Totopos",
            description = "Aguacate fresco, jitomate, cebolla y limón",
            detailedDescription = "Guacamole preparado al momento con aguacates Hass maduros, jitomate, cebolla morada, cilantro fresco, limón y un toque de chile serrano. Servido con totopos crujientes hechos en casa y salsas de la casa.",
            category = "comidas",
            imageRes = R.drawable.guacamole_totopos,
            price = 75.0
        )
    )

    return desayunosItems + comidasItems
} 