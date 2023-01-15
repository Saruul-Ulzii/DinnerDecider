package com.sw06d120.miuquiz.database.daos

import androidx.room.*
import com.sw06d120.miuquiz.database.entities.ChoiceAndQuestion
import com.sw06d120.miuquiz.database.entities.Question
import com.sw06d120.miuquiz.database.entities.QuestionWithChoices

@Dao
interface QuestionDao {
    @Insert
    fun addQuestion(question: Question): Long

    @Query("SELECT * FROM QUESTION ORDER BY id DESC")
    fun getAllQuestions(): List<Question>

    @Query("SELECT * FROM QUESTION")
    fun getQuestionWithChoices(): List<QuestionWithChoices>

    @Update
    fun updateQuestion(question: Question)

    @Delete
    fun deleteQuestion(question: Question)

    @Query("DELETE FROM QUESTION")
    fun deleteAll()
}