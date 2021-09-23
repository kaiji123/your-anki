package com.example.youranki

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.youranki.database.AnkiType

@Database(entities = [AnkiData::class, AnkiType::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ankiDao(): AnkiDao
    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "ankiDatabase" // Database Name
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}