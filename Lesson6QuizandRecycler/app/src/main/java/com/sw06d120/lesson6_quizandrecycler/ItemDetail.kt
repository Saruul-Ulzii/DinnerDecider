package com.sw06d120.lesson6_quizandrecycler

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.single_item.view.*

class ItemDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        var item = intent.getSerializableExtra("item") as Item

        supportActionBar?.setTitle(item.title)

        itemTitle.setText(item.title)
        itemPrice.setText(item.price.toString())
        itemColor.setText(item.color)
        itemDesc.setText(item.desc)

        val context: Context = itemPhoto.getContext()
        val id: Int = context.getResources().getIdentifier(item.image, "drawable", context.getPackageName())
        itemPhoto.setImageResource(id)
    }
}