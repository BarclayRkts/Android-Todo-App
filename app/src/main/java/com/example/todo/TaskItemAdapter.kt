package com.example.todo

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import jdk.javadoc.internal.doclets.toolkit.util.DocPath.parent

import android.R

import android.view.LayoutInflater
import android.widget.TextView

// A bridge that tells the recyler view how we display the data we give ir

class TaskItemAdapter(val listOfItems: List<String>, val longClickListener: OnLongClickListener) : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>(){

    interface OnLongClickListener {
        fun onItemLongClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Get data model bases on position
        val item = listOfItems.get(position)

        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Store refrences to to elements in our layout view
        val textView: TextView

        init{
            textView = itemView.findViewById(R.id.text1)

            itemView.setOnLongClickListener{
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }
    }


}