package com.example.habittracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habittracker.models.Habit

class HabitViewModel : ViewModel() {
    private val _habits = MutableLiveData(emptyList<Habit>())
    val habits: LiveData<List<Habit>> = _habits

    fun addHabit(habit: Habit) {
        _habits.value = _habits.value?.plus(habit)
    }

    fun updateHabit(index: Int, habit: Habit) {
        _habits.value = _habits.value?.toMutableList()?.apply { this[index] = habit }
    }
}