package com.sw06d120.miuquiz.database.daos

import androidx.room.*
import com.sw06d120.miuquiz.database.entities.Question

@Dao
interface QuestionDao {
    @Insert
    fun addQuestion(question: Question)

    @Query("SELECT * FROM QUESTION ORDER BY id DESC")
    fun getAllQuestions(): List<Question>

    @Update
    fun updateQuestion(question: Question)

    @Delete
    fun deleteQuestion(question: Question)
}