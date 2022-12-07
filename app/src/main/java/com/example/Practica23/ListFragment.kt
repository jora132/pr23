package com.example.Practica23

import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Practica23.R

class ListFragment : Fragment() {
    private val listItems : ArrayList<ListItem> = ArrayList()
    private val listAdapter = ListAdapter(listItems)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readItems()
        if (listItems.size == 0) {
            createItems()
            readItems()
        }
        listAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.list_view)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
    private fun createItems() {
        val dbHelper = TestDbHelper(requireContext())
        val db = dbHelper.writableDatabase
        for (i : Int in 0..100) {
            val values = ContentValues().apply {
                put(TestDbContract.TestEntry.COLUMN_NAME_TITLE, "item $i")
            }
            val newRowId = db?.insert(TestDbContract.TestEntry.TABLE_NAME, null, values)
        }

    }
    private fun readItems() {
        listItems.clear()
        val dbHelper = TestDbHelper(requireContext())
        val db = dbHelper.readableDatabase
        val projection = arrayOf(BaseColumns._ID, TestDbContract.TestEntry.COLUMN_NAME_TITLE)

        val sortOrder = "${TestDbContract.TestEntry.COLUMN_NAME_TITLE} DESC"

        val cursor = db.query(
            TestDbContract.TestEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val title = getString(getColumnIndexOrThrow(TestDbContract.TestEntry.COLUMN_NAME_TITLE))
                val item = ListItem(itemId, title)
                listItems.add(item)
            }
        }
        cursor.close()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

}