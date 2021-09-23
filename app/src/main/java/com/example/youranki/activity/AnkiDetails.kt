package com.example.youranki.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.youranki.AppDatabase
import com.example.youranki.R
import java.lang.IndexOutOfBoundsException


class AnkiDetails : AppCompatActivity()  {
    var questionCounter = 0;
    var questionString= "";
    var answerString = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anki_details)






        val db = AppDatabase.getInstance(this)

        var intent: Intent = getIntent()
        val message = intent.getStringExtra("ankitype")
        val ankiQuestion = findViewById(R.id.ankiDetailQuestion) as TextView
        ankiQuestion.text= questionString;
        val nextButton= findViewById(R.id.nextButton) as Button
        val finishButton = findViewById<Button>(R.id.finishButton)
        finishButton.setOnClickListener {
            val intent = Intent(this, Home::class.java)

            startActivity(intent)
        }
        try {
            val ANKIS=  db.ankiDao().getAllTypeData(message.toString())
            val list = mutableListOf<Int>()
            for (i in ANKIS){
                list.add(i.uid)
            }


            val deleteButton = findViewById<Button>(R.id.deleteButton)
            deleteButton.setOnClickListener {
                val deleteUid = list.get(questionCounter)
                db.ankiDao().deleteAnkiByUid(deleteUid)
                val intent = Intent(this, AnkiDetails::class.java)
                intent.putExtra("ankitype",message.toString() )
                startActivity(intent)

            }
            questionString = ANKIS.get(questionCounter).ankiQuestion.toString()
            answerString = ANKIS.get(questionCounter).ankiAnswer.toString()
            ankiQuestion.text= questionString


            val answerView = findViewById(R.id.answerView) as TextView

            val showButton= findViewById(R.id.showAnswer) as Button
            showButton.setOnClickListener {
                answerView.text= answerString
            }
            val question = findViewById(R.id.questionText) as TextView
            question.text= questionCounter.toString()

            if (questionCounter==ANKIS.size -1){
                nextButton.visibility= View.INVISIBLE
            }
            nextButton.setOnClickListener {

                questionCounter= questionCounter +1
                question.text= questionCounter.toString()
                questionString = ANKIS.get(questionCounter).ankiQuestion.toString()
                answerString = ANKIS.get(questionCounter).ankiAnswer.toString()
                if (questionCounter==ANKIS.size -1){
                    nextButton.visibility= View.INVISIBLE
                    finishButton.visibility=View.VISIBLE
                }
                ankiQuestion.text = questionString
                answerView.text = ""
                Log.d("ankisize", ANKIS.size.toString())


            }
        }
        catch (e:IndexOutOfBoundsException){
            ankiQuestion.text = "No data inserted"
            nextButton.visibility= View.INVISIBLE
        }




        val ankiButton = findViewById<Button>(R.id.addAnkiButton)
        ankiButton.setOnClickListener {
            val intent = Intent(this, CreateAnki::class.java)
            intent.putExtra("ankitype",message.toString())
            startActivity(intent)
        }

        val removeButton = findViewById<Button>(R.id.removeButton)
        removeButton.setOnClickListener {
            db.ankiDao().deleteAnkiType(message.toString())
            db.ankiDao().deleteAnkis(message.toString())
            val intent = Intent(this, Home::class.java)

            startActivity(intent)

        }


    }
}