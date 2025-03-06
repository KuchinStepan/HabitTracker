package com.example.habittracker.models

import com.example.habittracker.types.HabitPriority
import com.example.habittracker.types.HabitType
import java.io.Serializable

data class Habit(
    var title: String,
    var description: String,
    var priority: HabitPriority,
    var type: HabitType,
    var count: Int,
    var frequency: Int,
    var color: Int
) : Serializable
