package com.example.habittracker.types

import com.example.habittracker.R

enum class HabitType(val stringValue: String, val id: Int) {
    Good("Хорошая", R.id.rbGood),
    Bad("Плохая", R.id.rbBad),
}