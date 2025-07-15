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
            imageUrl = "https://images.unsplash.com/photo-1528207776546-365bb710ee93?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        MenuItem(
            id = 2,
            name = "Pancakes de Chocolate",
            description = "Con chispas de chocolate y jarabe de chocolate",
            category = "DESAYUNOS",
            price = 95.0,
            imageUrl = "https://images.unsplash.com/photo-1506084868230-bb9d95c24759?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 3,
            name = "Sándwich de Desayuno",
            description = "Con huevo, jamón, tocino o queso, en pan de caja",
            category = "DESAYUNOS",
            price = 75.0,
            imageUrl = "https://images.unsplash.com/photo-1539252554453-80ab65ce3586?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 4,
            name = "Huevos Rancheros",
            description = "Huevos estrellados con salsa ranchera y frijoles",
            category = "DESAYUNOS",
            price = 90.0,
            imageUrl = "https://images.unsplash.com/photo-1551218808-94e220e084d2?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 5,
            name = "Avena con Fruta",
            description = "Avena caliente con frutas frescas y miel",
            category = "DESAYUNOS",
            price = 65.0,
            imageUrl = "https://images.unsplash.com/photo-1702648982253-8b851013e81f?q=80&w=735&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),

        // COMIDAS
        MenuItem(
            id = 6,
            name = "Hamburguesa Clásica",
            description = "Carne, lechuga, tomate, cebolla y queso",
            category = "COMIDAS",
            price = 120.0,
            imageUrl = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 7,
            name = "Pollo a la Parrilla",
            description = "Pechuga de pollo con verduras asadas",
            category = "COMIDAS",
            price = 140.0,
            imageUrl = "https://images.unsplash.com/photo-1532550907401-a500c9a57435?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 8,
            name = "Tacos de Carne",
            description = "3 tacos con carne asada, cebolla y cilantro",
            category = "COMIDAS",
            price = 85.0,
            imageUrl = "https://plus.unsplash.com/premium_photo-1661730314652-911662c0d86e?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        MenuItem(
            id = 9,
            name = "Ensalada César",
            description = "Lechuga, crutones, queso parmesano y aderezo césar",
            category = "COMIDAS",
            price = 95.0,
            imageUrl = "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 10,
            name = "Quesadillas",
            description = "Tortilla con queso y opción de pollo o carne",
            category = "COMIDAS",
            price = 80.0,
            imageUrl = "https://images.unsplash.com/photo-1599974579688-8dbdd335c77f?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 11,
            name = "Pasta Alfredo",
            description = "Pasta con salsa alfredo y pollo",
            category = "COMIDAS",
            price = 110.0,
            imageUrl = "https://images.unsplash.com/photo-1662197480393-2a82030b7b83?q=80&w=688&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),

        // BEBIDAS
        MenuItem(
            id = 12,
            name = "Café Americano",
            description = "Café negro recién preparado",
            category = "BEBIDAS",
            price = 35.0,
            imageUrl = "https://images.unsplash.com/photo-1509042239860-f550ce710b93?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 13,
            name = "Cappuccino",
            description = "Café con leche vaporizada y espuma",
            category = "BEBIDAS",
            price = 45.0,
            imageUrl = "https://images.unsplash.com/photo-1572442388796-11668a67e53d?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 14,
            name = "Jugo de Naranja",
            description = "Jugo natural de naranja recién exprimido",
            category = "BEBIDAS",
            price = 40.0,
            imageUrl = "https://images.unsplash.com/photo-1621506289937-a8e4df240d0b?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 15,
            name = "Smoothie de Fresa",
            description = "Smoothie con fresas, yogurt y miel",
            category = "BEBIDAS",
            price = 55.0,
            imageUrl = "https://images.unsplash.com/photo-1553530666-ba11a7da3888?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 16,
            name = "Refresco",
            description = "Coca-Cola, Pepsi, Sprite o Fanta",
            category = "BEBIDAS",
            price = 30.0,
            imageUrl = "https://images.unsplash.com/photo-1581636625402-29b2a704ef13?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 17,
            name = "Agua Fresca",
            description = "Horchata, jamaica o tamarindo",
            category = "BEBIDAS",
            price = 25.0,
            imageUrl = "https://plus.unsplash.com/premium_photo-1687354250465-9a0b07b53a2f?q=80&w=688&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),

        // POSTRES
        MenuItem(
            id = 18,
            name = "Helado de Vainilla",
            description = "Helado artesanal con topping a elegir",
            category = "POSTRES",
            price = 50.0,
            imageUrl = "https://images.unsplash.com/photo-1570197788417-0e82375c9371?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 19,
            name = "Pastel de Chocolate",
            description = "Rebanada de pastel de chocolate húmedo",
            category = "POSTRES",
            price = 65.0,
            imageUrl = "https://images.unsplash.com/photo-1578985545062-69928b1d9587?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 20,
            name = "Flan Casero",
            description = "Flan tradicional con caramelo",
            category = "POSTRES",
            price = 55.0,
            imageUrl = "https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 21,
            name = "Gelatina",
            description = "Gelatina de diferentes sabores",
            category = "POSTRES",
            price = 35.0,
            imageUrl = "https://images.unsplash.com/photo-1488477181946-6428a0291777?w=400&h=400&fit=crop"
        ),
        MenuItem(
            id = 22,
            name = "Churros",
            description = "Churros con azúcar y canela",
            category = "POSTRES",
            price = 45.0,
            imageUrl = "https://images.unsplash.com/photo-1549007953-2f2dc0b24019?w=400&h=400&fit=crop"
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