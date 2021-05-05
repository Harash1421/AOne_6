package com.example.aone_6

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Books(context:Context) : SQLiteOpenHelper(context, "book.db", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table book (Id integer primary key autoincrement, BookName text, Price integer)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

}