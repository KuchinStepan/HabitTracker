package com.example.habittracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.habittracker.constants.IntentExtraKeys
import com.example.habittracker.models.Habit
import com.example.habittracker.types.HabitPriority
import com.example.habittracker.types.HabitType
import com.example.habittracker.types.getStringValue

class HabitActivity : AppCompatActivity() {
    private var habit: Habit? = null
    private var index: Int = -1
    private var priority: HabitPriority = HabitPriority.High
    private var type: HabitType = HabitType.Good

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val spinner = findViewById<Spinner>(R.id.spinnerPriority)
        val rgType = findViewById<RadioGroup>(R.id.rgType)
        val etCount = findViewById<EditText>(R.id.etCount)
        val etFrequency = findViewById<EditText>(R.id.etFrequency)
        val btnSave = findViewById<Button>(R.id.btnSave)

        habit = intent.getSerializableExtra(IntentExtraKeys.HABIT) as? Habit
        index = intent.getIntExtra(IntentExtraKeys.INDEX, -1)

        habit?.let {
            priority = it.priority
            etTitle.setText(it.title)
            etDescription.setText(it.description)
            etCount.setText(it.count.toString())
            etFrequency.setText(it.frequency.toString())
            rgType.check(it.type.id)
        }

        rgType.setOnCheckedChangeListener { _, id ->
            type = HabitType.entries.first { entry -> entry.id == id }
        }

        val priorityList = HabitPriority.entries.map { it.getStringValue(this) }
        ArrayAdapter(
            this,
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

        btnSave.setOnClickListener {
            val newHabit = Habit(
                etTitle.text.toString(),
                etDescription.text.toString(),
                priority,
                type,
                if (etCount.text.isNotEmpty()) etCount.text.length.toString().toInt() else 0,
                if (etFrequency.text.isNotEmpty()) etFrequency.text.length.toString().toInt() else 0,
                0xFF00FF
            )

            val resultIntent = Intent()
            resultIntent.putExtra(IntentExtraKeys.HABIT, newHabit)
            resultIntent.putExtra(IntentExtraKeys.INDEX, index)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
