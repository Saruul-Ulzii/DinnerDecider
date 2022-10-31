package com.sw06d120.lesson4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shopping_category.*
import kotlinx.android.synthetic.main.activity_walmart_login.*

class ShoppingCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_category)

        var username = intent.getStringExtra("username")
        txtWelcome.text = "Welcome " + username
    }
}