package com.sw06d120.miuquiz.classes

import java.io.Serializable

data class Answer(
    val questionId: Long,
    var answer: String,
    val isCorrect: Boolean
) : Serializable
