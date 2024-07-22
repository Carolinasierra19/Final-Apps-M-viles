package com.example.nuevoproyecto.data

object TaskList {
    val listOfTasks = mutableListOf(
        Task(
            title = "Estudio Matemáticas",
            description = "Estudiar para el examen de matemáticas",
            itemsNeeded = listOf("Libro de Matemáticas", "Cuaderno", "Lápiz", "Goma de borrar", "Calculadora")
        ),
        Task(
            title = "Estudio Física",
            description = "Estudiar para el examen de física",
            itemsNeeded = listOf("Libro de Física", "Cuaderno", "Lápiz", "Goma de borrar", "Regla", "Transportador")
        ),
        Task(
            title = "Comprar Comida",
            description = "Ir al supermercado y comprar comida",
            itemsNeeded = listOf("Lista de compras", "Dinero", "Bolsa reutilizable", "Cartera", "Celular")
        ),
        Task(
            title = "Hacer Ejercicio",
            description = "Hacer una hora de ejercicio en el gimnasio",
            itemsNeeded = listOf("Ropa deportiva", "Zapatillas", "Botella de agua", "Toalla", "Música")
        ),
        Task(
            title = "Estudio Química",
            description = "Estudiar para el examen de química",
            itemsNeeded = listOf("Libro de Química", "Cuaderno", "Lápiz", "Goma de borrar", "Calculadora", "Tabla periódica")
        )
    )

    fun addTask(task: Task) {
        listOfTasks.add(task)
    }

    fun deleteTask(task: Task) {
        listOfTasks.remove(task)
    }
}




