package com.example.drivethrurestaurante.data.model

// Data class para los elementos del menú
data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val price: Double,
    val imageUrl: String? = null
)

// Categorías disponibles
enum class MenuCategory(val displayName: String) {
    DESAYUNOS("Desayunos"),
    COMIDAS("Comidas"),
    BEBIDAS("Bebidas"),
    POSTRES("Postres")
}

// Objeto que contiene todos los datos del menú
object MenuData {

    val menuItems = listOf(
        // DESAYUNOS
        MenuItem(
            id = 1,
            name = "Pancakes Clásicos",
            description = "Con mantequilla, miel, frutas o chocolate",
            category = "DESAYUNOS",
            price = 85.0,
            imageUrl = "https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img,w_1080,h_675/https:/www.yogurtamazonas.com/wp-content/uploads/2025/01/PANCAKES-SIN-FONDO.png"
        ),
        MenuItem(
            id = 2,
            name = "Pancakes de Chocolate",
            description = "Con chispas de chocolate y jarabe de chocolate",
            category = "DESAYUNOS",
            price = 95.0,
            imageUrl = "https://static.vecteezy.com/system/resources/previews/057/673/175/non_2x/pancakes-with-chocolate-on-top-free-png.png"
        ),
        MenuItem(
            id = 3,
            name = "Sándwich de Desayuno",
            description = "Con huevo, jamón, tocino o queso, en pan de caja",
            category = "DESAYUNOS",
            price = 75.0,
            imageUrl = "https://lovefoodfeed.com/wp-content/uploads/2021/07/BLT-bacon-lettuce-tomato-sandwich-02wp.webp"
        ),
        MenuItem(
            id = 4,
            name = "Huevos Rancheros",
            description = "Huevos estrellados con salsa ranchera y frijoles",
            category = "DESAYUNOS",
            price = 90.0,
            imageUrl = "https://www.santitas.com/sites/santitas.com/files//2022-10/huevos-rancheros-detail.png"
        ),
        MenuItem(
            id = 5,
            name = "Avena con Fruta",
            description = "Avena caliente con frutas frescas y miel",
            category = "DESAYUNOS",
            price = 65.0,
            imageUrl = "https://static.vecteezy.com/system/resources/previews/021/350/872/non_2x/white-bowl-with-oatmeal-and-mix-fruit-isolated-on-a-transparent-background-png.png"
        ),

        // COMIDAS
        MenuItem(
            id = 6,
            name = "Hamburguesa Clásica",
            description = "Carne, lechuga, tomate, cebolla y queso",
            category = "COMIDAS",
            price = 120.0,
            imageUrl = "https://www.polloregio.com/wp-content/uploads/2018/05/Updated-Menu-Items-Edited-Burger-Regio-min.png"
        ),
        MenuItem(
            id = 7,
            name = "Pollo a la Parrilla",
            description = "Pechuga de pollo con verduras asadas",
            category = "COMIDAS",
            price = 140.0,
            imageUrl = "https://tofuu.getjusto.com/orioneat-local/resized2/TaDRLYBeSc7Ty8aeW-300-x.webp"
        ),
        MenuItem(
            id = 8,
            name = "Tacos de Carne",
            description = "3 tacos con carne asada, cebolla y cilantro",
            category = "COMIDAS",
            price = 85.0,
            imageUrl = "https://www.polloregio.com/wp-content/uploads/2022/08/2022_Pollo_Regio_August00208-Edit-1-1024x682.png"
        ),
        MenuItem(
            id = 9,
            name = "Ensalada César",
            description = "Lechuga, crutones, queso parmesano y aderezo césar",
            category = "COMIDAS",
            price = 95.0,
            imageUrl = "https://tofuu.getjusto.com/orioneat-local/resized2/x439LJPRWKpoZB2k2-2400-x.webp"
        ),
        MenuItem(
            id = 10,
            name = "Quesadillas",
            description = "Tortilla con queso y opción de pollo o carne",
            category = "COMIDAS",
            price = 80.0,
            imageUrl = "https://media.wawa.com/i/wawa/BreakfastQuesadillas_TallHeroBanner_02082023"
        ),
        MenuItem(
            id = 11,
            name = "Pasta Alfredo",
            description = "Pasta con salsa alfredo y pollo",
            category = "COMIDAS",
            price = 110.0,
            imageUrl = "https://aquamarseafood.com/wp-content/uploads/2023/10/Surimi-Alfredo-Aquamar.webp"
        ),

        // BEBIDAS
        MenuItem(
            id = 12,
            name = "Café Americano",
            description = "Café negro recién preparado",
            category = "BEBIDAS",
            price = 35.0,
            imageUrl = "https://yellowrabbit.mx/cdn/shop/files/pngtree-americano-coffee-beans-transparent-white-background-png-image_9097377_1000x1000.png?v=1693514503"
        ),
        MenuItem(
            id = 13,
            name = "Cappuccino",
            description = "Café con leche vaporizada y espuma",
            category = "BEBIDAS",
            price = 45.0,
            imageUrl = "https://discountcoffee.mx/cdn/shop/products/Disenosintitulo-38.png?v=1645556502"
        ),
        MenuItem(
            id = 14,
            name = "Jugo de Naranja",
            description = "Jugo natural de naranja recién exprimido",
            category = "BEBIDAS",
            price = 40.0,
            imageUrl = "https://static.vecteezy.com/system/resources/previews/050/591/823/non_2x/fresh-orange-juice-in-a-clear-glass-bottle-with-whole-and-halved-oranges-free-png.png"
        ),
        MenuItem(
            id = 15,
            name = "Smoothie de Fresa",
            description = "Smoothie con fresas, yogurt y miel",
            category = "BEBIDAS",
            price = 55.0,
            imageUrl = "https://static.vecteezy.com/system/resources/previews/065/621/320/non_2x/a-strawberry-smoothie-cup-on-transparent-background-png.png"
        ),
        MenuItem(
            id = 16,
            name = "Refresco",
            description = "Coca-Cola, Pepsi, Sprite o Fanta",
            category = "BEBIDAS",
            price = 30.0,
            imageUrl = "https://www.coca-colaentuhogar.com/media/catalog/category/CCETH_CORP_2025_BTNDECATEGORIA_REFRESCOS.png"
        ),
        MenuItem(
            id = 17,
            name = "Agua Fresca",
            description = "Horchata, jamaica o tamarindo",
            category = "BEBIDAS",
            price = 25.0,
            imageUrl = "https://pnghq.com/wp-content/uploads/download-hd-vaso-de-agua-fresca-png-download-free-png-images-300x231.png"
        ),

        // POSTRES
        MenuItem(
            id = 18,
            name = "Helado de Vainilla",
            description = "Helado artesanal con topping a elegir",
            category = "POSTRES",
            price = 50.0,
            imageUrl = "https://www.ben-jerrys.es/files/live/sites/systemsite/files/EU%20Specific%20Assets/Flavors/Product%20Assets/Vanilla%20Ice%20Cream/vanilla_rgb_bulk.png"
        ),
        MenuItem(
            id = 19,
            name = "Pastel de Chocolate",
            description = "Rebanada de pastel de chocolate húmedo",
            category = "POSTRES",
            price = 65.0,
            imageUrl = "https://morningstarcafe.com.mx/uploads/large/1654541664_pastelchocolate.png"
        ),
        MenuItem(
            id = 20,
            name = "Flan Casero",
            description = "Flan tradicional con caramelo",
            category = "POSTRES",
            price = 55.0,
            imageUrl = "https://www.flancasero.com/wp-content/uploads/2019/11/pngocean.com-8.png"
        ),
        MenuItem(
            id = 21,
            name = "Gelatina",
            description = "Gelatina de diferentes sabores",
            category = "POSTRES",
            price = 35.0,
            imageUrl = "https://static.wixstatic.com/media/ee6e5b_66768618c947461faacfe44a4255cb77~mv2.png/v1/fill/w_480,h_480,al_c,q_85,usm_0.66_1.00_0.01,enc_avif,quality_auto/ee6e5b_66768618c947461faacfe44a4255cb77~mv2.png"
        ),
        MenuItem(
            id = 22,
            name = "Churros",
            description = "Churros con azúcar y canela",
            category = "POSTRES",
            price = 45.0,
            imageUrl = "https://keithsfoods.com.au/wp-content/uploads/2017/12/Spanish-Donuts.png"
        )
    )

    // Función para obtener elementos por categoría
    fun getItemsByCategory(category: String): List<MenuItem> {
        return menuItems.filter { it.category == category }
    }

    // Función para obtener todas las categorías
    fun getAllCategories(): List<String> {
        return menuItems.map { it.category }.distinct()
    }

    // Función para obtener snacks (desayunos en este caso)
    fun getSnacks(): List<MenuItem> {
        return getItemsByCategory("DESAYUNOS")
    }

    // Función para obtener todas las comidas (excluyendo snacks)
    fun getMeals(): List<MenuItem> {
        return menuItems.filter { it.category != "DESAYUNOS" }
    }
}