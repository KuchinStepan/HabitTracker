package com.example.habittracker

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModel
import com.example.habittracker.models.Habit
import com.example.habittracker.types.HabitPriority
import com.example.habittracker.types.HabitType
import com.example.habittracker.types.getStringValue

class HabitActivityViewModel  : ViewModel() {
    var type: HabitType = HabitType.Good
    var habit: Habit? = null
    var index: Int = -1
    var priority: HabitPriority = HabitPriority.High

    fun onTypeChecked(id: Int) {
        type = HabitType.entries.first { entry -> entry.id == id }
    }

    fun setupSpinner(context: Context, spinner: Spinner) {
        val priorityList = HabitPriority.entries.map { it.getStringValue(context) }

        ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            priorityList
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.setSelection(priority.index)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                priority = HabitPriority.entries[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) { }
        }
    }
}