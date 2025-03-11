package com.example.habittracker.types

import android.content.Context
import androidx.core.content.ContextCompat.getString
import com.example.habittracker.R

enum class HabitType(val id: Int) {
    Good(R.id.rbGood),
    Bad(R.id.rbBad),
}

fun HabitType.getStringValue(context: Context): String {
    return when (this) {
        HabitType.Good -> getString(context, R.string.type_good)
        HabitType.Bad -> getString(context, R.string.type_bad)
    }
}
