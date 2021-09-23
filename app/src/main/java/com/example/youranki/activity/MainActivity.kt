package com.example.youranki.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.youranki.AnkiDao
import com.example.youranki.AppDatabase
import com.example.youranki.R
import java.lang.Runnable


class MainActivity : AppCompatActivity() {

lateinit var ankiDao : AnkiDao;

    /*
    lateinit var ANKI :AnkiData
    suspend fun getData()= coroutineScope{
       ;
        val ankiDATA = AnkiData(1,"question","answer", "easy")
        val one = async {
            val listAnki = ankiDao.insertAnki(ankiDATA)
        }
        val two = async{
            val listdata= ankiDao.getAll()
            return@async listdata

        }
        one.await()
        return@coroutineScope two.await()



    }
    fun main()= runBlocking{
        withContext(Dispatchers.IO){
            val two =getData()


        }

    }

     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //delay in ms
        //delay in ms
        val DELAY = 3000

        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this, Home::class.java)

            startActivity(intent)
        }, DELAY.toLong())
        val ankidatabase = AppDatabase.getInstance(this)

    }
}