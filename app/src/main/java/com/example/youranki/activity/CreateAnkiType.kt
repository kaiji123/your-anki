package com.example.youranki.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.youranki.AppDatabase
import com.example.youranki.R
import com.example.youranki.database.AnkiType

class CreateAnkiType : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_anki_type)

        val db = AppDatabase.getInstance(this)



        val addButton = findViewById<Button>(R.id.addTypeButton)
        addButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.ankiText)
            db.ankiDao().insertAnkiType(AnkiType(0,editText.text.toString()))
            Toast.makeText(this, "data inserted ",
                Toast.LENGTH_LONG).show();

        }


        val backButton = findViewById<Button>(R.id.backHomeButton)
        backButton.setOnClickListener {
            val intent = Intent(this, Home::class.java)

            startActivity(intent)
        }

    }
}