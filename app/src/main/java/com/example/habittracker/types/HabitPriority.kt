package com.example.habittracker.types

enum class HabitPriority(val value: String, val index: Int) {
    High("Высокий приоритет", 0),
    Medium("Средний приоритет", 1),
    Low("Низкий приоритет", 2)
}