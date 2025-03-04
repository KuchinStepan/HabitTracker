package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.models.Habit
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private val viewModel: HabitViewModel by viewModels()
    private lateinit var adapter: HabitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fabAddHabit = findViewById<FloatingActionButton>(R.id.fabAddHabit)

        adapter = HabitAdapter { habit, index ->
            val intent = Intent(this, HabitActivity::class.java)
            intent.putExtra("habit", habit)
            intent.putExtra("index", index)
            startActivityForResult(intent, 100)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.habits.observe(this) { habits ->
            adapter.submitList(habits)
        }

        fabAddHabit.setOnClickListener {
            val intent = Intent(this, HabitActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val habit = data?.getSerializableExtra("habit") as? Habit
            val index = data?.getIntExtra("index", -1) ?: -1
            if (habit != null) {
                if (index == -1) {
                    viewModel.addHabit(habit)
                } else {
                    viewModel.updateHabit(index, habit)
                }
            }
        }
    }
}