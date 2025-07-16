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
            id = 5,
            name = "Waffles",
            description = "Con mantequilla, miel, frutas o chocolate.",
            detailedDescription = "Waffles belgas hechos al momento con masa artesanal, servidos con mantequilla de la casa, frutas frescas y miel de maple. Perfectamente dorados por fuera y suaves por dentro, con la opción de agregar crema batida o chocolate.",
            category = "desayunos",
            imageRes = R.drawable.waffles,
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

    val bebidasFriasItems = listOf(
        MenuItem(
            id = 21,
            name = "Agua de horchata",
            description = "Con canela y leche condensada",
            detailedDescription = "Refrescante agua de horchata casera, preparada con arroz, canela y un toque de leche condensada para endulzar.",
            category = "bebidas",
            imageRes = R.drawable.horchata,
            price = 30.0
        ),
        MenuItem(
            id = 22,
            name = "Agua de jamaica",
            description = "Refrescante y natural",
            detailedDescription = "Agua de jamaica 100% natural, hecha con flor de jamaica y endulzada al gusto. Perfecta para acompañar cualquier platillo.",
            category = "bebidas",
            imageRes = R.drawable.jamaica,
            price = 30.0
        ),
        MenuItem(
            id = 23,
            name = "Limonada mineral",
            description = "Con limón fresco y hielo",
            detailedDescription = "Limonada preparada con agua mineral, jugo de limón recién exprimido y un toque de azúcar. Servida con hielo y rodajas de limón.",
            category = "bebidas",
            imageRes = R.drawable.limonada,
            price = 35.0
        ),
        MenuItem(
            id = 24,
            name = "Té helado",
            description = "De durazno, limón o frutos rojos",
            detailedDescription = "Té helado refrescante, disponible en sabores de durazno, limón o una mezcla de frutos rojos. Endulzado y servido con hielo.",
            category = "bebidas",
            imageRes = R.drawable.te,
            price = 35.0
        ),
        MenuItem(
            id = 25,
            name = "Smoothie de Fruta",
            description = "Fresa, mango, plátano o combinados",
            detailedDescription = "Smoothie de fruta fresca a tu elección. Elige entre fresa, mango, plátano o una combinación de tus favoritos. Hecho con leche y hielo.",
            category = "bebidas",
            imageRes = R.drawable.smoothie,
            price = 45.0
        ),
        MenuItem(
            id = 26,
            name = "Refrescos",
            description = "Lata o botella",
            detailedDescription = "Disfruta de una variedad de refrescos de la familia Coca-Cola. Disponibles en lata o botella.",
            category = "bebidas",
            imageRes = R.drawable.coca,
            price = 25.0
        ),
        MenuItem(
            id = 27,
            name = "Agua Embotellada",
            description = "500ml",
            detailedDescription = "Agua purificada embotellada de 500ml. La opción más saludable para mantenerte hidratado.",
            category = "bebidas",
            imageRes = R.drawable.embotellada,
            price = 20.0
        )
    )

    val postresItems = listOf(
        MenuItem(
            id = 28,
            name = "Brownie",
            description = "Con chocolate derretido y nueces",
            detailedDescription = "Delicioso brownie casero con chocolate derretido y nueces. Servido caliente con una bola de helado de vainilla opcional.",
            category = "postres",
            imageRes = R.drawable.brownie,
            price = 45.0
        ),
        MenuItem(
            id = 29,
            name = "Crepa",
            description = "Con chocolate derretido y frutas",
            detailedDescription = "Crepa suave y delgada rellena con chocolate derretido y frutas frescas. Espolvoreada con azúcar glass.",
            category = "postres",
            imageRes = R.drawable.crepa,
            price = 50.0
        ),
        MenuItem(
            id = 30,
            name = "Dona",
            description = "Glaseada con chocolate",
            detailedDescription = "Dona fresca y esponjosa glaseada con chocolate derretido. Perfecta para acompañar con café o como postre.",
            category = "postres",
            imageRes = R.drawable.dona,
            price = 35.0
        )
    )

    return desayunosItems + comidasItems + promocionesItems + platosFuertesItems + bebidasFriasItems + postresItems
} 