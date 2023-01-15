package com.sw06d120.miuquiz.database.entities

import androidx.room.*

@Entity
data class Question(
    @PrimaryKey val id: Long,
    val question: String
)

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Question::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("answer"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Answer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val answer: String,
    val questionId: Long
)

data class AnswerAndQuestion(
    @Embedded val question: Question,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    val answer: Answer
)