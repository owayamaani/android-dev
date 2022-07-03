package com.example.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.students.adapter.studentsAdapter
import com.example.students.entities.students
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.rvSTUDENTS)
        this.genStudents(recyclerView)
    }
    private fun genStudents(recyclerView: RecyclerView){
        val queue = Volley.newRequestQueue(this)
        val url="https://mtekcdnstoragev2.blob.core.windows.net/admin/mcfish/students.json"
        val req = StringRequest(
            Request.Method.GET,url,
            {
                val students = mutableListOf<students>()
                val ans = JSONArray(it)
                for (i in 0..ans.length() -1) {
                    val student = ans.getJSONObject(i)
                    students.add(
                        students(
                            student.getString( "adminNo"),
                            student.getString("name")
                        )
                    )
                }

            populateRecyclerView(recyclerView, students)
    })
    {
        Log.e("MainActivity", "fetchStudents:", it)

    }
    queue.add(req)
}
private fun populateRecyclerView(recyclerView: RecyclerView, students: MutableList<students>){
    val adapter = studentsAdapter(students)
    recyclerView.adapter=adapter
    recyclerView.layoutManager=LinearLayoutManager(this)
    adapter.notifyDataSetChanged()
}
}