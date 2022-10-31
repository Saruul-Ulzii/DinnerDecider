package com.sw06d120.lesson4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shopping_category.*

class ShoppingCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_category)

        var username = intent.getStringExtra("username")
        txtWelcome.text = "Welcome, " + username
    }

    fun clicked(view: View) {
        var category = "Unknown"
        when(view.id) {
            R.id.lblBeauty, R.id.imgBeauty -> category = "Beauty"
            R.id.lblClothes, R.id.imgClothes -> category = "Clothes"
            R.id.lblFood, R.id.imgFood -> category = "Food"
            R.id.lblElectronics, R.id.imgElectronics -> category = "Electronics"
        }

        Toast.makeText(this, "You have chosen the ${category} category of shopping", Toast.LENGTH_SHORT).show()
    }
}