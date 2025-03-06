package com.example.habittracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.habittracker.models.Habit
import com.example.habittracker.types.HabitPriority

class HabitActivity : AppCompatActivity() {
    private var habit: Habit? = null
    private var index: Int = -1
    private lateinit var priority: HabitPriority

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val spinner: Spinner = findViewById(R.id.spinnerPriority)

        habit = intent.getSerializableExtra("habit") as? Habit
        index = intent.getIntExtra("index", -1)
        priority = habit?.priority ?: HabitPriority.High

        val priorityList = HabitPriority.entries.map { it.value }

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

        habit?.let {
            etTitle.setText(it.title)
            etDescription.setText(it.description)
        }

        btnSave.setOnClickListener {
            val newHabit = Habit(
                etTitle.text.toString(),
                etDescription.text.toString(),
                priority,
                "Хорошая",
                3,
                7,
                0xFF00FF
            )

            val resultIntent = Intent()
            resultIntent.putExtra("habit", newHabit)
            resultIntent.putExtra("index", index)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
