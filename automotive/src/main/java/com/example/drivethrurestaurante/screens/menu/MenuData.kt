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

    val promocionesItems = listOf(
        MenuItem(
            id = 10,
            name = "Combo Familiar",
            description = "El que alcanza para todos",
            detailedDescription = "Un combo perfecto para compartir en familia. Incluye 8 piezas de nuestro delicioso pollo asado, arroz, frijoles charros, ensalada de col, tortillas y una variedad de salsas caseras.",
            category = "promociones",
            imageRes = R.drawable.promocion1,
            price = 350.0
        ),
        MenuItem(
            id = 11,
            name = "La Promo Más Familiar",
            description = "Llévate un refresco de 2.5 Lts.",
            detailedDescription = "En la compra de nuestro Combo Familiar de 16 piezas, llévate un refresco de 2.5 Lts. ¡totalmente gratis! La mejor opción para disfrutar con todos.",
            category = "promociones",
            imageRes = R.drawable.promocion2,
            price = 550.0
        )
    )

    val platosFuertesItems = listOf(
        MenuItem(
            id = 12,
            name = "Hamburguesa clásica",
            description = "Con papas fritas y aderezo de la casa",
            detailedDescription = "Nuestra hamburguesa clásica con carne de res de primera, queso cheddar, lechuga, tomate y cebolla en pan brioche. Acompañada de papas fritas crujientes.",
            category = "platos_fuertes",
            imageRes = R.drawable.hamburguesa_clasica,
            price = 120.0
        ),
        MenuItem(
            id = 13,
            name = "Pechuga a la plancha",
            description = "Con arroz, ensalada y tortillas",
            detailedDescription = "Jugosa pechuga de pollo a la plancha, marinada en finas hierbas. Se sirve con una porción de arroz blanco, ensalada fresca y tortillas de maíz.",
            category = "platos_fuertes",
            imageRes = R.drawable.pechuga_plancha,
            price = 150.0
        ),
        MenuItem(
            id = 14,
            name = "Milanesa de Res o Pollo",
            description = "Con puré de papa y ensalada",
            detailedDescription = "Elige entre res o pollo, empanizada y frita a la perfección hasta quedar dorada y crujiente. Acompañada de un cremoso puré de papa y ensalada.",
            category = "platos_fuertes",
            imageRes = R.drawable.milanesa_res,
            price = 160.0
        ),
        MenuItem(
            id = 15,
            name = "Enchiladas Verdes/Rojas",
            description = "Con pollo, crema, queso y arroz",
            detailedDescription = "Tres enchiladas rellenas de pollo deshebrado, bañadas en tu elección de salsa verde o roja. Cubiertas con crema, queso fresco y cebolla morada.",
            category = "platos_fuertes",
            imageRes = R.drawable.enchiladas,
            price = 130.0
        ),
        MenuItem(
            id = 16,
            name = "Carne Asada",
            description = "Con cebollitas, nopales, papas y tortillas",
            detailedDescription = "Tierno filete de res asado a la parrilla, servido con cebollitas cambray, nopales asados, papas fritas y tortillas calientes.",
            category = "platos_fuertes",
            imageRes = R.drawable.carne_asada,
            price = 180.0
        ),
        MenuItem(
            id = 17,
            name = "Pollo en Mole",
            description = "Con arroz rojo y ajonjolí",
            detailedDescription = "Una pieza de pollo bañada en nuestro tradicional mole poblano, espolvoreado con ajonjolí tostado. Se acompaña con arroz a la mexicana.",
            category = "platos_fuertes",
            imageRes = R.drawable.mole,
            price = 140.0
        ),
        MenuItem(
            id = 18,
            name = "Lasagna",
            description = "Capas de pasta con carne, bechamel y gratinado",
            detailedDescription = "Clásica lasagna a la boloñesa, con capas de pasta fresca, salsa de carne, salsa bechamel y una generosa capa de queso mozzarella y parmesano gratinado.",
            category = "platos_fuertes",
            imageRes = R.drawable.lasagna,
            price = 160.0
        ),
        MenuItem(
            id = 19,
            name = "Pollo a la Parmesana",
            description = "Empanizado, con salsa pomodoro y mozzarella",
            detailedDescription = "Pechuga de pollo empanizada, cubierta con salsa de tomate pomodoro y queso mozzarella derretido, horneada a la perfección.",
            category = "platos_fuertes",
            imageRes = R.drawable.pollo_parmesana,
            price = 170.0
        ),
        MenuItem(
            id = 20,
            name = "Pozole Rojo o Verde",
            description = "Sólo viernes y fines de semana",
            detailedDescription = "Tradicional pozole mexicano, disponible en versión roja o verde, con carne de cerdo, maíz pozolero y todos sus acompañamientos: lechuga, rábano, cebolla y orégano.",
            category = "platos_fuertes",
            imageRes = R.drawable.pozole,
            price = 110.0
        )
    )

    return desayunosItems + comidasItems + promocionesItems + platosFuertesItems
} 