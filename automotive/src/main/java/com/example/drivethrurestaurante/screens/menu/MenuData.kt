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
            name = "Pancakes Clásicos",
            description = "Con mantequilla, miel, frutas o chocolate.",
            detailedDescription = "Nuestros pancakes son preparados con una mezcla especial de harina, huevo, mantequilla, leche y un toque de vainilla, logrando una textura suave y esponjosa. Se sirven calientes, acompañados de mantequilla derretida y miel de maple 100% natural.",
            category = "desayunos",
            imageRes = R.drawable.pancakes,
            price = 85.0
        ),
        MenuItem(
            id = 2,
            name = "Sándwich",
            description = "Con huevo, jamón, tocino o queso, en pan de caja",
            detailedDescription = "Delicioso sándwich preparado con pan recién horneado, huevo revuelto, jamón de pavo, tocino crujiente y queso gouda derretido. Acompañado de papas a la francesa y ensalada fresca.",
            category = "desayunos",
            imageRes = R.drawable.sandwich,
            price = 95.0
        ),
        MenuItem(
            id = 3,
            name = "Waffles",
            description = "Con mantequilla, miel, frutas o chocolate.",
            detailedDescription = "Waffles belgas hechos al momento con masa artesanal, servidos con mantequilla de la casa, frutas frescas y miel de maple. Perfectamente dorados por fuera y suaves por dentro, con la opción de agregar crema batida o chocolate.",
            category = "desayunos",
            imageRes = R.drawable.waffles,
            price = 80.0
        ),
        MenuItem(
            id = 4,
            name = "Huevos Rancheros",
            description = "Huevos estrellados con salsa ranchera y frijoles",
            detailedDescription = "Huevos Rancheros con verdura.",
            category = "desayunos",
            imageRes = R.drawable.huevos_r,
            price = 90.0
        ),
        MenuItem(
            id = 5,
            name = "Avena con Fruta",
            description = "Avena caliente con frutas frescas y miel",
            detailedDescription = "Deliciosa mezcla de avena cocida lentamente, servida con una colorida selección de frutas frescas de temporada. Un desayuno ligero, nutritivo y naturalmente dulce.",
            category = "desayunos",
            imageRes = R.drawable.avena_fruta,
            price = 65.0
        )

    )
// COMIDAS
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
            price = 85.0
        ),
        MenuItem(
            id = 9,
            name = "Guacamole con Totopos",
            description = "Aguacate fresco, jitomate, cebolla y limón",
            detailedDescription = "Guacamole preparado al momento con aguacates Hass maduros, jitomate, cebolla morada, cilantro fresco, limón y un toque de chile serrano. Servido con totopos crujientes hechos en casa y salsas de la casa.",
            category = "comidas",
            imageRes = R.drawable.guacamole_totopos,
            price = 95.0
        ),
        MenuItem(
            id = 10,
            name = "Quesadillas",
            description = "Tortilla con queso y opción de pollo o carne",
            detailedDescription = "Tortillas doradas al comal rellenas de queso fundido y tu elección de guisado. Un clásico mexicano que combina sabor, calidez y tradición en cada bocado.",
            category = "comidas",
            imageRes = R.drawable.quesadillas,
            price = 75.0
        ),
        MenuItem(
            id = 11,
            name = "Pasta Alfredo",
            description = "Pasta con salsa alfredo y pollo",
            detailedDescription = "Fettuccine bañado en una suave y cremosa salsa Alfredo hecha con mantequilla, crema y queso parmesano. Un platillo reconfortante con un toque italiano irresistible.",
            category = "comidas",
            imageRes = R.drawable.pasta_alfredo,
            price = 110.0
        )
    )

    val bebidasFriasItems = listOf(
        MenuItem(
            id = 12,
            name = "Agua de horchata",
            description = "Con canela y leche condensada",
            detailedDescription = "Refrescante agua de horchata casera, preparada con arroz, canela y un toque de leche condensada para endulzar.",
            category = "bebidas",
            imageRes = R.drawable.horchata,
            price = 30.0
        ),
        MenuItem(
            id = 13,
            name = "Agua de jamaica",
            description = "Refrescante y natural",
            detailedDescription = "Agua de jamaica 100% natural, hecha con flor de jamaica y endulzada al gusto. Perfecta para acompañar cualquier platillo.",
            category = "bebidas",
            imageRes = R.drawable.jamaica,
            price = 30.0
        ),
        MenuItem(
            id = 14,
            name = "Limonada mineral",
            description = "Con limón fresco y hielo",
            detailedDescription = "Limonada preparada con agua mineral, jugo de limón recién exprimido y un toque de azúcar. Servida con hielo y rodajas de limón.",
            category = "bebidas",
            imageRes = R.drawable.limonada,
            price = 35.0
        ),
        MenuItem(
            id = 15,
            name = "Té helado",
            description = "De durazno, limón o frutos rojos",
            detailedDescription = "Té helado refrescante, disponible en sabores de durazno, limón o una mezcla de frutos rojos. Endulzado y servido con hielo.",
            category = "bebidas",
            imageRes = R.drawable.te,
            price = 55.0
        ),
        MenuItem(
            id = 16,
            name = "Smoothie de Fruta",
            description = "Fresa, mango, plátano o combinados",
            detailedDescription = "Smoothie de fruta fresca a tu elección. Elige entre fresa, mango, plátano o una combinación de tus favoritos. Hecho con leche y hielo.",
            category = "bebidas",
            imageRes = R.drawable.smoothie,
            price = 30.0
        ),
        MenuItem(
            id = 17,
            name = "Refrescos",
            description = "Lata o botella",
            detailedDescription = "Disfruta de una variedad de refrescos de la familia Coca-Cola. Disponibles en lata o botella.",
            category = "bebidas",
            imageRes = R.drawable.coca,
            price = 25.0
        ),
    )

    val postresItems = listOf(
        MenuItem(
            id = 18,
            name = "Brownie",
            description = "Con chocolate derretido y nueces",
            detailedDescription = "Delicioso brownie casero con chocolate derretido y nueces. Servido caliente con una bola de helado de vainilla opcional.",
            category = "postres",
            imageRes = R.drawable.brownie,
            price = 50.0
        ),
        MenuItem(
            id = 19,
            name = "Crepa de Chocolate",
            description = "Con chocolate derretido y frutas",
            detailedDescription = "Crepa suave y delgada rellena con chocolate derretido y frutas frescas. Espolvoreada con azúcar glass.",
            category = "postres",
            imageRes = R.drawable.crepa,
            price = 50.0
        ),
        MenuItem(
            id = 20,
            name = "Dona de Chocolate",
            description = "Glaseada con chocolate",
            detailedDescription = "Dona fresca y esponjosa glaseada con chocolate derretido. Perfecta para acompañar con café o como postre.",
            category = "postres",
            imageRes = R.drawable.dona,
            price = 55.0
        ),
        MenuItem(
            id = 21,
            name = "Gelatina",
            description = "Gelatina de diferentes sabores",
            detailedDescription = "Postre fresco y ligero, elaborado con sabores frutales y una textura suave que encanta a chicos y grandes. El final perfecto para cualquier comida..",
            category = "postres",
            imageRes = R.drawable.gelatina,
            price = 35.0
        ),
        MenuItem(
            id = 22,
            name = "Churros",
            description = "GChurros con azúcar y canela",
            detailedDescription = "Crujientes por fuera y suaves por dentro, espolvoreados con azúcar y canela. Servidos calientitos, perfectos para acompañar con chocolate o cajeta.",
            category = "postres",
            imageRes = R.drawable.churros,
            price = 45.0
        )
    )






    return desayunosItems + comidasItems  + bebidasFriasItems + postresItems
}