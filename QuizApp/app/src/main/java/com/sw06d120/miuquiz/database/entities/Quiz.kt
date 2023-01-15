package com.sw06d120.miuquiz.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
foreignKeys = [
ForeignKey(
    entity = Answer::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("answer"),
    onDelete = ForeignKey.CASCADE
),
ForeignKey(
    entity = Question::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("question"),
    onDelete = ForeignKey.CASCADE
)])
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val answer: String,
    val question: String
)