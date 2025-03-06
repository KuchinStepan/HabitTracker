package com.example.habittracker

import androidx.lifecycle.ViewModel
import com.example.habittracker.models.Habit

class HabitViewModel : ViewModel() {
    private val _habits: MutableList<Habit> = mutableListOf()
    val habits = _habits

    fun addHabit(habit: Habit) {
        _habits.add(habit)
    }

    fun updateHabit(index: Int, habit: Habit) {
        _habits[index] = habit
    }
}