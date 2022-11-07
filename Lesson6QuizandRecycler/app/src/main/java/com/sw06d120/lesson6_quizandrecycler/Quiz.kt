package com.sw06d120.lesson6_quizandrecycler

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_quiz.*
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Quiz : AppCompatActivity() {
    var correctCount = 0
    var totalQuestions = 3.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkQuiz(view: View) {
        correctCount = 0

        if(radio2.isChecked) {
            correctCount++
        }

        if(check2.isChecked) {
            correctCount++
        }

        if(check3.isChecked) {
            correctCount++
        }

        var currentDate = LocalDateTime.now()
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        var curDate = currentDate.format(formatter)

        Toast.makeText(this,"Congratulations!", Toast.LENGTH_SHORT).show()
        Toast.makeText(this,"Date ${curDate}", Toast.LENGTH_SHORT).show()
        Toast.makeText(this,"Your achieved ${(String.format("%.2f", correctCount / totalQuestions * 100)) }%", Toast.LENGTH_LONG).show()
    }

    fun resetQuiz(view: View) {
        correctCount = 0

        check1.isChecked = false
        check2.isChecked = false
        check3.isChecked = false

        radio1.isChecked = false
        radio2.isChecked = false
        radio3.isChecked = false
    }
}