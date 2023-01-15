package com.sw06d120.miuquiz.classes

import java.io.Serializable

data class Question(
    val id: Long,
    val question: String,
    val choices: Array<Choice>
) : Serializable {
}
