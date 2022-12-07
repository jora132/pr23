package com.example.Practica23

import android.provider.BaseColumns

object TestDbContract {
    object TestEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_NAME_TITLE = "title"
    }
}