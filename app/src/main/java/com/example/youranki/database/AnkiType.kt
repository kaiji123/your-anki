package com.example.youranki.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ankiType")
data class AnkiType(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "ankiTypeName") val ankiTypeName: String?,

)