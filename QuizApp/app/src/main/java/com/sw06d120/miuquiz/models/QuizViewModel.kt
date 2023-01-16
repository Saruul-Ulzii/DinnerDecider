package com.sw06d120.miuquiz.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sw06d120.miuquiz.classes.Answer
import com.sw06d120.miuquiz.database.entities.QuestionWithChoices

class QuizViewModel : ViewModel() {
    private var _correctAnswerCounter = MutableLiveData(0)
    var correctAnswerCounter: LiveData<Int> = _correctAnswerCounter

    var questions: List<QuestionWithChoices> = ArrayList()
    private var answers: List<Answer> = ArrayList()

    private var _currentIndex = MutableLiveData(0)
    var currentIndex: LiveData<Int> = _currentIndex

    private var answersLiveData = MutableLiveData<List<Answer>>()

    fun reset() {
        _currentIndex.value = 0
        _correctAnswerCounter.value = 0
    }

    fun getAnswers(): MutableLiveData<List<Answer>> {
        answersLiveData.value = answers
        return answersLiveData
    }

    fun addCorrectScore() {
        _correctAnswerCounter.value = _correctAnswerCounter.value!! + 1
    }

    fun getCurrentQuestion(): QuestionWithChoices {
        return questions[_currentIndex.value!!]
    }

    fun goNext() {
        if (_currentIndex.value!! < questions.size) {
            _currentIndex.value = _currentIndex.value!! + 1
        } else {
            _currentIndex.value = 0
        }
    }
}