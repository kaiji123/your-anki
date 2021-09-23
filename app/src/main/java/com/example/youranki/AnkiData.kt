package com.example.youranki

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ankiTable")
data class AnkiData(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "ankiQuestion") val ankiQuestion: String?,
    @ColumnInfo(name = "ankiAnswer") val ankiAnswer: String?,
    @ColumnInfo(name = "ankiName") val ankiName: String?
)