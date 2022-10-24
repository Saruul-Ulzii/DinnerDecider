package com.sw06d120.dinnerdecider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var foods: ArrayList<String> = ArrayList(arrayListOf("Hamburger", "Pizza", "Mexican", "American", "Chinese"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add_food.setOnClickListener{
            lbl_food_name.setText(txt_new_name.text)
            foods.add(txt_new_name.text.toString())
            txt_new_name.setText("")
        }

        btn_decide.setOnClickListener {
            lbl_food_name.setText(foods.random())
        }
    }
}