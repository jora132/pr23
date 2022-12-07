package com.example.Practica23
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Practica23.R

class ListAdapter(private val listEntries : ArrayList<ListItem>)
    : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView : TextView
        init {
            titleView = itemView.findViewById(R.id.title_view)
        }
        fun setTitle(string : String) {
            titleView.text = string
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setTitle(listEntries[position].title)
    }

    override fun getItemCount(): Int {
        return listEntries.size
    }
}