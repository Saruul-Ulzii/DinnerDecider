package com.sw06d120.miuquiz.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sw06d120.miuquiz.classes.Answer
import com.sw06d120.miuquiz.classes.Question

class QuizViewModel : ViewModel() {
    var questions: Map<Int, Question> = HashMap()
    private var answers: Map<Int, Answer> = HashMap()

    private var _currentIndex = MutableLiveData(0)
    var currentIndex: LiveData<Int> = _currentIndex

    private var answersLiveData = MutableLiveData<Map<Int, Answer>>()

    fun reset() {
        _currentIndex.value = 0
    }

    fun getAnswers(): MutableLiveData<Map<Int, Answer>> {
        answersLiveData.value = answers
        return answersLiveData
    }

    fun setAnswerByQuestionId(questionId: Int, answer: String) {

    }

    fun goNext() {
        if(_currentIndex.value!! < 15) {
            _currentIndex.value = _currentIndex.value!! + 1
        } else {
            _currentIndex.value = 0
        }
    }
}