package com.example.students.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.students.R
import com.example.students.entities.students

class studentsAdapter ( private val students: MutableList<students>): RecyclerView.Adapter<studentsAdapter.viewHolder>(){
    class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var tvNAME: TextView
        private lateinit var tvADMINNO: TextView
        init {
            this.tvNAME = view.findViewById(R.id.tvNAME)
            this.tvADMINNO = view.findViewById(R.id.tvADMINNO)
        }
        fun populate (students: students) {
            this.tvNAME.setText(students.name)
            this.tvADMINNO.setText(students.adminNo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.students_layouts, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.populate(students[position])
    }

    override fun getItemCount(): Int = students.size

}
