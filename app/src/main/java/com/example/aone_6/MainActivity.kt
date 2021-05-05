package com.example.aone_6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var book = Books(this)
        bu_Save.setOnClickListener {
            var db = book.writableDatabase
            db.execSQL("insert into book values (?,?,?)", arrayOf(ed_Id.text.toString(), ed_Book_Name.text.toString(),
                                                                ed_Price.text.toString()))
            Toast.makeText(this, "Book Saved", Toast.LENGTH_SHORT).show()
            ed_Id.setText("")
            ed_Book_Name.setText("")
            ed_Price.setText("")
        }
        registerForContextMenu(bu_Hold)
    }

    //Context Menu Add
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.add(1,1,1,"Load")
        menu!!.add(1,2,1,"Update")
        menu!!.add(1,3,1,"Delete")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var book = Books(this)
        when(item!!.itemId){
            1 -> {
                var db = book.readableDatabase
                var cur = db.rawQuery("select * from book where Id = ?", arrayOf(ed_Id.text.toString()))
                if (cur.count == 0) {
                    Toast.makeText(this, "Book Not Failed", Toast.LENGTH_SHORT).show()
                } else {
                    cur.moveToFirst()
                    ed_Book_Name.setText(cur.getString(1))
                    ed_Price.setText(cur.getString(2))
                    Toast.makeText(this, "Book Loaded", Toast.LENGTH_SHORT).show()
                }
            }
            2 -> {
                var db = book.writableDatabase
                db.execSQL("update book set Name = ? , Price = ? where Id = ?", arrayOf(ed_Book_Name.text.toString(),
                        ed_Price.text.toString(), ed_Id.text.toString()))
                Toast.makeText(this, "Update Complete", Toast.LENGTH_SHORT).show()
            }
            3 -> {
                var db = book.writableDatabase
                db.execSQL("delete from book where Id = ?", arrayOf(ed_Id.text.toString()))
                ed_Id.setText("")
                ed_Book_Name.setText("")
                ed_Price.setText("")
                Toast.makeText(this, "Book Delete Complete", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }

    //Menu

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.me_Library -> {
                startActivity(Intent(this,BookLibraryActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}