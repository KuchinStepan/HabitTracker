package com.example.habittracker.types

import android.content.Context
import androidx.core.content.ContextCompat.getString
import com.example.habittracker.R

enum class HabitPriority(val index: Int) {
    High(0),
    Medium(1),
    Low(2)
}

fun HabitPriority.getStringValue(context: Context): String {
    return when (this) {
        HabitPriority.High -> getString(context, R.string.high_priority)
        HabitPriority.Medium -> getString(context, R.string.medium_priority)
        HabitPriority.Low -> getString(context, R.string.low_priority)
    }
}