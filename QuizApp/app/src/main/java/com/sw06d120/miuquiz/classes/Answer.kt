package com.sw06d120.miuquiz.classes

import java.io.Serializable

data class Answer(
    val questionId: Int,
    val answer: String): Serializable
