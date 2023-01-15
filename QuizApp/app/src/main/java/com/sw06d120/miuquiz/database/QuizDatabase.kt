package com.sw06d120.miuquiz.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sw06d120.miuquiz.database.daos.QuizDao

abstract class QuizDatabase() : RoomDatabase() {
    abstract fun getQuizDao(): QuizDao

    companion object {
        @Volatile
        private var instance: QuizDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            QuizDatabase::class.java,
            "quizdatabase"
        ).build()
    }
}