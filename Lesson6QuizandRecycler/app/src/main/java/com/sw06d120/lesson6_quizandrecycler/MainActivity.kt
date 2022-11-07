package com.sw06d120.lesson6_quizandrecycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showQuiz(view: View) {
        var quiz: Intent = Intent(this, Quiz::class.java)
        startActivity(quiz)
    }

    fun showShopping(view: View) {
        var shopping = Intent(this, CategoryItems::class.java)
        startActivity(shopping)
    }
}