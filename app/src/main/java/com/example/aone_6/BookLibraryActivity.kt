package com.example.aone_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_book_library.*

class BookLibraryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_library)
        var obj = Books(this)
        var db = obj.readableDatabase
        var cur = db.rawQuery("select * from book", null)
        if (cur.count > 0){
            cur.moveToFirst()
            var list = ArrayList<String>()
            while (!cur.isAfterLast){
                list.add("Id: " + cur.getString(0) + "\n" + "Book Name: " + cur.getString(1) + "\n" + "Price: "
                + cur.getString(2))
                cur.moveToNext()
            }
            var adapter = ArrayAdapter(this, R.layout.book_layout, list)
            library_Books.adapter = adapter
        }
    }
}