package com.example.habittracker.models

import com.example.habittracker.types.HabitPriority
import java.io.Serializable

data class Habit(
    var title: String,
    var description: String,
    var priority: HabitPriority,
    var type: String,
    var count: Int,
    var frequency: Int,
    var color: Int
) : Serializable
