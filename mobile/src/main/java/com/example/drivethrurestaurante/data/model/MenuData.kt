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
            price = 85.0
        ),
        MenuItem(
            id = 2,
            name = "Pancakes de Chocolate",
            description = "Con chispas de chocolate y jarabe de chocolate",
            category = "DESAYUNOS",
            price = 95.0
        ),
        MenuItem(
            id = 3,
            name = "Sándwich de Desayuno",
            description = "Con huevo, jamón, tocino o queso, en pan de caja",
            category = "DESAYUNOS",
            price = 75.0
        ),
        MenuItem(
            id = 4,
            name = "Huevos Rancheros",
            description = "Huevos estrellados con salsa ranchera y frijoles",
            category = "DESAYUNOS",
            price = 90.0
        ),
        MenuItem(
            id = 5,
            name = "Avena con Fruta",
            description = "Avena caliente con frutas frescas y miel",
            category = "DESAYUNOS",
            price = 65.0
        ),

        // COMIDAS
        MenuItem(
            id = 6,
            name = "Hamburguesa Clásica",
            description = "Carne, lechuga, tomate, cebolla y queso",
            category = "COMIDAS",
            price = 120.0
        ),
        MenuItem(
            id = 7,
            name = "Pollo a la Parrilla",
            description = "Pechuga de pollo con verduras asadas",
            category = "COMIDAS",
            price = 140.0
        ),
        MenuItem(
            id = 8,
            name = "Tacos de Carne",
            description = "3 tacos con carne asada, cebolla y cilantro",
            category = "COMIDAS",
            price = 85.0
        ),
        MenuItem(
            id = 9,
            name = "Ensalada César",
            description = "Lechuga, crutones, queso parmesano y aderezo césar",
            category = "COMIDAS",
            price = 95.0
        ),
        MenuItem(
            id = 10,
            name = "Quesadillas",
            description = "Tortilla con queso y opción de pollo o carne",
            category = "COMIDAS",
            price = 80.0
        ),
        MenuItem(
            id = 11,
            name = "Pasta Alfredo",
            description = "Pasta con salsa alfredo y pollo",
            category = "COMIDAS",
            price = 110.0
        ),

        // BEBIDAS
        MenuItem(
            id = 12,
            name = "Café Americano",
            description = "Café negro recién preparado",
            category = "BEBIDAS",
            price = 35.0
        ),
        MenuItem(
            id = 13,
            name = "Cappuccino",
            description = "Café con leche vaporizada y espuma",
            category = "BEBIDAS",
            price = 45.0
        ),
        MenuItem(
            id = 14,
            name = "Jugo de Naranja",
            description = "Jugo natural de naranja recién exprimido",
            category = "BEBIDAS",
            price = 40.0
        ),
        MenuItem(
            id = 15,
            name = "Smoothie de Fresa",
            description = "Smoothie con fresas, yogurt y miel",
            category = "BEBIDAS",
            price = 55.0
        ),
        MenuItem(
            id = 16,
            name = "Refresco",
            description = "Coca-Cola, Pepsi, Sprite o Fanta",
            category = "BEBIDAS",
            price = 30.0
        ),
        MenuItem(
            id = 17,
            name = "Agua Fresca",
            description = "Horchata, jamaica o tamarindo",
            category = "BEBIDAS",
            price = 25.0
        ),

        // POSTRES
        MenuItem(
            id = 18,
            name = "Helado de Vainilla",
            description = "Helado artesanal con topping a elegir",
            category = "POSTRES",
            price = 50.0
        ),
        MenuItem(
            id = 19,
            name = "Pastel de Chocolate",
            description = "Rebanada de pastel de chocolate húmedo",
            category = "POSTRES",
            price = 65.0
        ),
        MenuItem(
            id = 20,
            name = "Flan Casero",
            description = "Flan tradicional con caramelo",
            category = "POSTRES",
            price = 55.0
        ),
        MenuItem(
            id = 21,
            name = "Gelatina",
            description = "Gelatina de diferentes sabores",
            category = "POSTRES",
            price = 35.0
        ),
        MenuItem(
            id = 22,
            name = "Churros",
            description = "Churros con azúcar y canela",
            category = "POSTRES",
            price = 45.0
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