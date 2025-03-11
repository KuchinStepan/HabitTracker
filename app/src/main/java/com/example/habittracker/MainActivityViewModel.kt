package com.example.habittracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habittracker.models.Habit

class MainActivityViewModel : ViewModel() {
    private val _habits = MutableLiveData<List<Habit>>(emptyList())
    val habits: LiveData<List<Habit>> = _habits

    fun addHabit(habit: Habit) {
        val updatedList = _habits.value.orEmpty() + habit
        _habits.value = updatedList
    }

    fun updateHabit(index: Int, habit: Habit) {
        val currentList = _habits.value.orEmpty().toMutableList()
        if (index < currentList.count()) {
            currentList[index] = habit
            _habits.value = currentList
        }
    }
}