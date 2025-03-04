package com.example.habittracker.models

import java.io.Serializable

data class Habit(
    var title: String,
    var description: String,
    var priority: String,
    var type: String,
    var count: Int,
    var frequency: Int,
    var color: Int
) : Serializable
