package com.sw06d120.miuquiz.database.daos

import androidx.room.*
import com.sw06d120.miuquiz.database.entities.Choice

@Dao
interface ChoiceDao {
    @Insert
    fun addChoice(choice: Choice)
    @Query("SELECT * FROM CHOICE ORDER BY id DESC")
    fun getAllChoices():List<Choice>
    @Update
    fun updateChoice(choice: Choice)
    @Delete
    fun deleteChoice(choice: Choice)
}