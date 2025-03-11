package com.example.habittracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.habittracker.constants.IntentExtraKeys
import com.example.habittracker.models.Habit

class HabitActivity : AppCompatActivity() {
    private val viewModel: HabitActivityViewModel by viewModels()

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

        viewModel.habit = intent.getSerializableExtra(IntentExtraKeys.HABIT) as? Habit
        viewModel.index = intent.getIntExtra(IntentExtraKeys.INDEX, -1)

        viewModel.habit?.let {
            viewModel.priority = it.priority
            etTitle.setText(it.title)
            etDescription.setText(it.description)
            etCount.setText(it.count.toString())
            etFrequency.setText(it.frequency.toString())
            rgType.check(it.type.id)
        }

        rgType.setOnCheckedChangeListener { _, id ->
            viewModel.onTypeChecked(id)
        }

        viewModel.setupSpinner(this, spinner)

        btnSave.setOnClickListener {
            val newHabit = Habit(
                etTitle.text.toString(),
                etDescription.text.toString(),
                viewModel.priority,
                viewModel.type,
                if (etCount.text.isNotEmpty()) etCount.text.length.toString().toInt() else 0,
                if (etFrequency.text.isNotEmpty()) etFrequency.text.length.toString().toInt() else 0,
                0xFF00FF
            )

            val resultIntent = Intent()
            resultIntent.putExtra(IntentExtraKeys.HABIT, newHabit)
            resultIntent.putExtra(IntentExtraKeys.INDEX, viewModel.index)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
