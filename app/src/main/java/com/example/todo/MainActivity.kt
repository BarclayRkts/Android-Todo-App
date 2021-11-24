package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val listOfTasks = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1. Detects when the user clicks on the add button
//        findViewById<Button>(R.id.button).setOnClickListener{
//            // Code here is going to execute when button is clicked
//            Log.i("Caren", "User clicked on button!")
//        }

        listOfTasks.add("Do Laundry")
        listOfTasks.add("Go for a walk")

        // Lookup the recyclerview in activity layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Create adapter passing in the sample user data
        val adapter = TaskItemAdapater(listOfTasks)

        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter

        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)

        val InputtedTask = findViewById<EditText>(R.id.addTaskField)

        // Set up the button and input field so that the user can enter a task and add it to the list
        findViewById<Button>(R.id.button).setOnClickListener{

            //Grab the text the user has inputted into TasksField
            val userInputtedTask = InputtedTask.text.toString()

            //Add the string to out list of tasks
            listOfTasks.add(userInputtedTask)

            //Notify adapter that our data has been updated
            adapter.notifyItemInserted(listOfTasks.size - 1)

            //Reset the text field
            InputtedTask.setText("")

        }

    }
}