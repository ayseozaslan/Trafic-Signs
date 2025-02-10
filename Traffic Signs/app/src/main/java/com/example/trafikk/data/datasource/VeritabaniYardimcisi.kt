package com.example.trafikk.data.datasource

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeritabaniYardimcisi(context: Context)
    :SQLiteOpenHelper(context,"sorularquizz.sqlite",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("""
            CREATE TABLE IF NOT EXISTS sorular (
                soru_id INTEGER PRIMARY KEY AUTOINCREMENT,
                soru_ad TEXT NOT NULL,
                soru_resim TEXT NOT NULL
            )
        """.trimIndent())
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL("DROP TABLE IF EXISTS sorular ")
        onCreate(db)
    }
}