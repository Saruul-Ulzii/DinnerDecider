package com.sw06d120.miuquiz.classes

import java.io.Serializable

data class Question(
    val id: Int,
    val question: Int,
    val choices: Array<Choice>): Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (id != other.id) return false
        if (question != other.question) return false
        if (!choices.contentEquals(other.choices)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + question
        result = 31 * result + choices.contentHashCode()
        return result
    }
}
