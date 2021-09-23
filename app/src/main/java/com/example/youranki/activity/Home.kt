package com.example.youranki.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

import android.content.Intent
import android.widget.Button
import android.widget.ListAdapter

import com.example.youranki.AppDatabase
import com.example.youranki.R


class Home : AppCompatActivity() {
   lateinit var listview:ListView;
    lateinit var list: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        listview = findViewById(R.id.listview)


        val db = AppDatabase.getInstance(this)
        val typelist=  db.ankiDao().getAllTypes()
        list = mutableListOf()
        for (i in typelist){
            i.ankiTypeName?.let { list.add(it) }
        }
        val arr = list.toTypedArray()
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_activated_1,
            arr
        )

        listview.adapter= adapter
        listview.setOnItemClickListener { parent, view, position, id ->
            //Toast.makeText(this, arr.get(position),Toast.LENGTH_SHORT).show()// The item that was clicked
        /*    val intent = Intent(this, BookDetailActivity::class.java)
            startActivity(intent)*/
            val intent = Intent(this, AnkiDetails::class.java)

            intent.putExtra("ankitype", arr.get(position));
            startActivity(intent)
        }

       // db.ankiDao().insertAnki(AnkiData(0, "hello", "qq", "w"))

        val createButton = findViewById<Button>(R.id.ankiGroupCreate)
        createButton.setOnClickListener {
            val intent = Intent(this, CreateAnkiType::class.java)

            startActivity(intent)
        }
    }





}