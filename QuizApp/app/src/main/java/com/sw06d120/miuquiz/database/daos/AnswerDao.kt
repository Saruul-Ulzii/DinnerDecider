package com.sw06d120.miuquiz.database.daos

import androidx.room.*
import com.sw06d120.miuquiz.database.entities.Answer

@Dao
interface AnswerDao {
    @Insert
    fun addAnswer(answer: Answer)

    @Query("SELECT * FROM ANSWER ORDER BY id DESC")
    fun getAllAnswers(): List<Answer>

    @Update
    fun updateAnswer(answer: Answer)

    @Delete
    fun deleteAnswer(answer: Answer)
}