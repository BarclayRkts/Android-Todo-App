package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                //1. Remove Item from list
                listOfTasks.removeAt(position)

                //2: Notidy the adpater somehing has changed
                adapter.notifyDataSetChanged()

                saveItems()

            }

        }

        //1. Detects when the user clicks on the add button
//        findViewById<Button>(R.id.button).setOnClickListener{
//            // Code here is going to execute when button is clicked
//            Log.i("Caren", "User clicked on button!")
//        }

//        listOfTasks.add("Do Laundry")
//        listOfTasks.add("Go for a walk")

        loadItems()

        // Lookup the recyclerview in activity layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Create adapter passing in the sample user data
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)

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

            saveItems()

        }

    }

    // Save the data that the user has inputted
    // Saving my writing and reading from a file

    //Create a method to get the file we need
    fun getDataFile() : File {
        //Every line is a specific task
        return File(filesDir, "data.txt")
    }

    //Load the items by reading every line in the data file
    fun loadItems() {
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        }catch (ioException: IOException){
            ioException.printStackTrace()
        }
    }

    //Save Items by writing them into our data file
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)

        }catch (ioException: IOException){
            ioException.printStackTrace()
        }
    }

}