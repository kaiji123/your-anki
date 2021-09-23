package com.example.youranki.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.youranki.AnkiData
import com.example.youranki.AppDatabase
import com.example.youranki.R
import com.example.youranki.database.AnkiType

class CreateAnki : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_anki)
        val db = AppDatabase.getInstance(this)
        var intent: Intent = getIntent()
        val ankitype = intent.getStringExtra("ankitype")


        val addButton = findViewById<Button>(R.id.addAnkiDetailButton)
        addButton.setOnClickListener {
            val questionText = findViewById<EditText>(R.id.ankiQuestionText)
            val answerText= findViewById<EditText>(R.id.ankiAnswerText)
            db.ankiDao().insertAnki(AnkiData(0,questionText.text.toString(),answerText.text.toString(),ankitype.toString()))
            Toast.makeText(this, "data inserted ",
                Toast.LENGTH_LONG).show();

        }


        val backButton = findViewById<Button>(R.id.backAnkiButton)
        backButton.setOnClickListener {
            val intent = Intent(this, AnkiDetails::class.java)
            intent.putExtra("ankitype",ankitype.toString() )
            startActivity(intent)
        }

    }
}