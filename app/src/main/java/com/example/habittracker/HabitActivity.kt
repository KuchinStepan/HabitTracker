package com.example.habittracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.habittracker.models.Habit

class HabitActivity : AppCompatActivity() {
    private var habit: Habit? = null
    private var index: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val btnSave = findViewById<Button>(R.id.btnSave)

        habit = intent.getSerializableExtra("habit") as? Habit
        index = intent.getIntExtra("index", -1)

        habit?.let {
            etTitle.setText(it.title)
            etDescription.setText(it.description)
        }

        btnSave.setOnClickListener {
            val newHabit = Habit(
                etTitle.text.toString(),
                etDescription.text.toString(),
                "Средний",
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
