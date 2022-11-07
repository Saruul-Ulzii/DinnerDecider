package com.sw06d120.lesson6_quizandrecycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CategoryItems : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_items)
        supportActionBar?.hide()
    }

    fun clicked(view: View) {
        var intent = Intent(this, CategoryList::class.java);
        intent.putExtra("selectedCategoryId", view.id.toString());
        startActivity(intent);
    }
}