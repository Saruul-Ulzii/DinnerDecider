package com.sw06d120.miuquiz.classes

import java.io.Serializable

data class Choice(
    val answer: String,
    val type: ChoiceType,
    val isCorrect: Boolean): Serializable

enum class ChoiceType {
    One,
    Many
}