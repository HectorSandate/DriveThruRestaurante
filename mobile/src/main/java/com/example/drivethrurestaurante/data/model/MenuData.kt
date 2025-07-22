//Mobil Data
package com.example.drivethrurestaurante.data.model
import com.example.drivethrurestaurante.R
// Data class para los elementos del menú
data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val price: Double,
    val imageUrl: String? = null,
    val imageRes: Int,
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
            imageUrl = "https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img,w_1080,h_675/https:/www.yogurtamazonas.com/wp-content/uploads/2025/01/PANCAKES-SIN-FONDO.png",
            imageRes = R.drawable.pancakes
        ),
        MenuItem(
            id = 2,
            name = "Sándwich",
            description = "Con huevo, jamón, tocino o queso, en pan de caja",
            category = "DESAYUNOS",
            price = 95.0,
            imageUrl = "https://static.vecteezy.com/system/resources/previews/057/673/175/non_2x/pancakes-with-chocolate-on-top-free-png.png",
            imageRes = R.drawable.sandwich
        ),
        MenuItem(
            id = 3,
            name = "Waffles",
            description = "Con mantequilla, miel, frutas o chocolate.",
            category = "DESAYUNOS",
            price = 75.0,
            imageUrl = "https://lovefoodfeed.com/wp-content/uploads/2021/07/BLT-bacon-lettuce-tomato-sandwich-02wp.webp",
            imageRes = R.drawable.waffles
        ),
        MenuItem(
            id = 4,
            name = "Huevos Rancheros",
            description = "Huevos estrellados con salsa ranchera y frijoles",
            category = "DESAYUNOS",
            price = 90.0,
            imageUrl = "https://www.santitas.com/sites/santitas.com/files//2022-10/huevos-rancheros-detail.png",
            imageRes = R.drawable.huevo_r
        ),
        MenuItem(
            id = 5,
            name = "Avena con Fruta",
            description = "Avena caliente con frutas frescas y miel",
            category = "DESAYUNOS",
            price = 65.0,
            imageUrl = "https://static.vecteezy.com/system/resources/previews/021/350/872/non_2x/white-bowl-with-oatmeal-and-mix-fruit-isolated-on-a-transparent-background-png.png",
            imageRes = R.drawable.avena_fruta
        ),

        // COMIDAS
        MenuItem(
            id = 6,
            name = "Ensalada César",
            description = "Lechuga romana, crutones, parmesano y aderezo",
            category = "COMIDAS",
            price = 95.0,
            imageUrl = "https://www.polloregio.com/wp-content/uploads/2018/05/Updated-Menu-Items-Edited-Burger-Regio-min.png",
            imageRes = R.drawable.ensalada_cesar
        ),
        MenuItem(
            id = 7,
            name = "Ensalada de Pollo Asado",
            description = "Con espinacas, manzana, nuez y vinagreta balsámica",
            category = "COMIDAS",
            price = 110.0,
            imageUrl = "https://tofuu.getjusto.com/orioneat-local/resized2/TaDRLYBeSc7Ty8aeW-300-x.webp",
            imageRes = R.drawable.ensalada_pollo
        ),
        MenuItem(
            id = 8,
            name = "Sopa del Día",
            description = "Pregunta por nuestra opción casera del día",
            category = "COMIDAS",
            price = 85.0,
            imageUrl = "https://www.polloregio.com/wp-content/uploads/2022/08/2022_Pollo_Regio_August00208-Edit-1-1024x682.png",
            imageRes = R.drawable.sopa_dia
        ),
        MenuItem(
            id = 9,
            name = "Guacamole con Totopos",
            description = "Aguacate fresco, jitomate, cebolla y limón",
            category = "COMIDAS",
            price = 95.0,
            imageUrl = "https://tofuu.getjusto.com/orioneat-local/resized2/x439LJPRWKpoZB2k2-2400-x.webp",
            imageRes = R.drawable.guacamole_totopos
        ),
        MenuItem(
            id = 10,
            name = "Quesadillas",
            description = "Tortilla con queso y opción de pollo o carne",
            category = "COMIDAS",
            price = 80.0,
            imageUrl = "https://media.wawa.com/i/wawa/BreakfastQuesadillas_TallHeroBanner_02082023",
            imageRes = R.drawable.quesadillas
        ),
        MenuItem(
            id = 11,
            name = "Pasta Alfredo",
            description = "Pasta con salsa alfredo y pollo",
            category = "COMIDAS",
            price = 110.0,
            imageUrl = "https://aquamarseafood.com/wp-content/uploads/2023/10/Surimi-Alfredo-Aquamar.webp",
            imageRes = R.drawable.pasta_alfredo
        ),

        // BEBIDAS
        MenuItem(
            id = 12,
            name = "Agua de horchata",
            description = "Con canela y leche condensada",
            category = "BEBIDAS",
            price = 35.0,
            imageUrl = "https://yellowrabbit.mx/cdn/shop/files/pngtree-americano-coffee-beans-transparent-white-background-png-image_9097377_1000x1000.png?v=1693514503",
            imageRes = R.drawable.horchata
        ),
        MenuItem(
            id = 13,
            name = "Agua de jamaica",
            description = "Refrescante y natural",
            category = "BEBIDAS",
            price = 45.0,
            imageUrl = "https://discountcoffee.mx/cdn/shop/products/Disenosintitulo-38.png?v=1645556502",
            imageRes = R.drawable.jamaica
        ),
        MenuItem(
            id = 14,
            name = "Limonada mineral",
            description = "Jugo natural de limon recién exprimido",
            category = "BEBIDAS",
            price = 40.0,
            imageUrl = "https://static.vecteezy.com/system/resources/previews/050/591/823/non_2x/fresh-orange-juice-in-a-clear-glass-bottle-with-whole-and-halved-oranges-free-png.png",
            imageRes = R.drawable.limonada
        ),
        MenuItem(
            id = 15,
            name = "Té helado",
            description = "De durazno, limón o frutos rojos",
            category = "BEBIDAS",
            price = 55.0,
            imageUrl = "https://static.vecteezy.com/system/resources/previews/065/621/320/non_2x/a-strawberry-smoothie-cup-on-transparent-background-png.png",
            imageRes = R.drawable.te
        ),
        MenuItem(
            id = 16,
            name = "Smoothie de Fruta",
            description = "Fresa, mango, plátano o combinados",
            category = "BEBIDAS",
            price = 30.0,
            imageUrl = "https://www.coca-colaentuhogar.com/media/catalog/category/CCETH_CORP_2025_BTNDECATEGORIA_REFRESCOS.png",
            imageRes = R.drawable.smoothie
        ),
        MenuItem(
            id = 17,
            name = "Refrescos",
            description = "Lata o botella",
            category = "BEBIDAS",
            price = 25.0,
            imageUrl = "https://pnghq.com/wp-content/uploads/download-hd-vaso-de-agua-fresca-png-download-free-png-images-300x231.png",
            imageRes = R.drawable.coca
        ),

        // POSTRES
        MenuItem(
            id = 18,
            name = "Brownie",
            description = "Con chocolate derretido y nueces",
            category = "POSTRES",
            price = 50.0,
            imageUrl = "https://www.ben-jerrys.es/files/live/sites/systemsite/files/EU%20Specific%20Assets/Flavors/Product%20Assets/Vanilla%20Ice%20Cream/vanilla_rgb_bulk.png",
            imageRes = R.drawable.brownie
        ),
        MenuItem(
            id = 19,
            name = "Crepa de Chocolate",
            description = "Con chocolate derretido",
            category = "POSTRES",
            price = 65.0,
            imageUrl = "https://morningstarcafe.com.mx/uploads/large/1654541664_pastelchocolate.png",
            imageRes = R.drawable.crepa
        ),
        MenuItem(
            id = 20,
            name = "Dona de Chocolate",
            description = "Flan tradicional con caramelo",
            category = "POSTRES",
            price = 55.0,
            imageUrl = "https://www.flancasero.com/wp-content/uploads/2019/11/pngocean.com-8.png",
            imageRes = R.drawable.dona
        ),
        MenuItem(
            id = 21,
            name = "Gelatina",
            description = "Gelatina de diferentes sabores",
            category = "POSTRES",
            price = 35.0,
            imageUrl = "https://static.wixstatic.com/media/ee6e5b_66768618c947461faacfe44a4255cb77~mv2.png/v1/fill/w_480,h_480,al_c,q_85,usm_0.66_1.00_0.01,enc_avif,quality_auto/ee6e5b_66768618c947461faacfe44a4255cb77~mv2.png",
            imageRes = R.drawable.gelatina
        ),
        MenuItem(
            id = 22,
            name = "Churros",
            description = "Churros con azúcar y canela",
            category = "POSTRES",
            price = 45.0,
            imageUrl = "https://keithsfoods.com.au/wp-content/uploads/2017/12/Spanish-Donuts.png",
            imageRes = R.drawable.churros
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