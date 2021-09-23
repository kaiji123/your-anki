package com.example.youranki

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.youranki.database.AnkiType
import kotlinx.coroutines.flow.Flow

@Dao
interface AnkiDao {
    @Query("SELECT * FROM ankiTable")
   fun getAll(): List<AnkiData>
    @Query("SELECT * FROM ankiType")
   fun getAllTypes(): List<AnkiType>


    @Query("SELECT * FROM ankiTable WHERE ankiName LIKE :ankiTypeString")
    fun getAllTypeData(ankiTypeString :String): List<AnkiData>


/*
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): AnkiData
    */


    @Insert(onConflict = REPLACE)
    fun insertAnki(anki:AnkiData)


    @Insert(onConflict = REPLACE)
    fun insertAnkiType(ankitype: AnkiType)

    @Delete
    fun delete(anki: AnkiData)

    @Query("DELETE FROM ankiType WHERE ankiTypeName LIKE :ankiTypeString")
    fun deleteAnkiType(ankiTypeString: String)


    @Query("DELETE FROM ankiTable WHERE ankiName LIKE :ankiTypeString")
    fun deleteAnkis(ankiTypeString: String)

    @Query("DELETE FROM ankiTable WHERE uid == :uuid")
    fun deleteAnkiByUid(uuid: Int)
}