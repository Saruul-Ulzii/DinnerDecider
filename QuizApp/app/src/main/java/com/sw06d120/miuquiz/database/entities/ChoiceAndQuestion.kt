package com.sw06d120.miuquiz.database.entities

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Question::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("question"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Choice(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val questionId: Long,
    val answer: String,
    val isCorrect: Boolean,
    val type: String,
)
data class ChoiceAndQuestion(
    @Embedded val question: Question,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    val choice: Choice
)