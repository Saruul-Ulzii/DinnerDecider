package com.sw06d120.miuquiz.database.daos

import androidx.room.*
import com.sw06d120.miuquiz.database.entities.Quiz

@Dao
interface QuizDao {
    @Insert
    fun addNote(quiz: Quiz)
    @Query("SELECT * FROM QUIZ ORDER BY id DESC")
    fun getAllQuizzes():List<Quiz>
    @Update
    fun updateQuiz(quiz: Quiz)
    @Delete
    fun deleteQuiz(quiz: Quiz)
}