package com.example.habittracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.models.Habit
import com.example.habittracker.types.getStringValue

class HabitAdapter(private val onItemClick: (Habit, Int) -> Unit) :
    RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    private var habits: List<Habit> = emptyList()

    fun submitList(newList: List<Habit>) {
        habits = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(habits[position])
        holder.itemView.setOnClickListener { onItemClick(habits[position], position) }
    }

    override fun getItemCount() = habits.size

    inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tvHabitCardTitle)
        private val description: TextView = itemView.findViewById(R.id.tvHabitCardDescription)
        private val priority: TextView = itemView.findViewById(R.id.tvHabitPriority)
        private val type: TextView = itemView.findViewById(R.id.tvHabitCardType)

        fun bind(habit: Habit) {
            title.text = habit.title
            description.text = habit.description
            priority.text = habit.priority.getStringValue(itemView.context)
            type.text = habit.type.getStringValue(itemView.context)
        }
    }
}
