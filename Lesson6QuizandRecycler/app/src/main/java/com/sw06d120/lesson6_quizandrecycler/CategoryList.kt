package com.sw06d120.lesson6_quizandrecycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_category_list.*

class CategoryList : AppCompatActivity(), MyAdapter.ItemSelectionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        supportActionBar?.hide()

        populateData()
    }

    val products = ArrayList<Item>()
    fun populateData() {
        products.add(Item(1, "Dell gaming laptop", 1200.0, "Black", "dell", "Make the World Your Workplace With intelligent features, Dell's latest Latitude laptops and 2-in-1s make the world your workplace."))
        products.add(Item(2, "ASUS laptop", 800.0, "Red", "asus", "Zenbook Pro 16X OLED is a no-compromise creator laptop with a wealth of innovative design features and a brand-new all-metal design."))
        products.add(Item(3, "MacBook Pro", 1600.0, "Silver", "mbp", "The most powerful MacBook Pro ever is here. With the blazing-fast M1 Pro or M1 Max chip — the first Apple silicon designed for pros — you get groundbreaking performance and amazing battery life. Add to that a stunning Liquid Retina XDR display, the best camera and audio ever in a Mac laptop, and all the ports you need."))
        products.add(Item(4, "HP", 900.0, "White", "hp", "Family, friends, obsessions, music, creations—Windows 11 is the one place for it all. With a fresh new feel and tools that make it easier to be efficient, it has what you need for whatever’s next.6"))
        products.add(Item(4, "Lenovo", 500.0, "White", "lenovo", "Special 30th Anniversary Edition October marks 30 years of ThinkPad and we’re commemorating this monumental occasion with a special, limited run of ThinkPad X1 Carbon 30th Anniversary Edition laptops! The special edition includes the classic ThinkPad logo in red, green, and blue (RGB)."))
        products.add(Item(4, "iMac", 2100.0, "White", "imac", "This extraordinary design is only possible thanks to M1, the first system on a chip for Mac. It makes iMac so thin and compact that it fits in more places than ever."))

        itemList.layoutManager = LinearLayoutManager(this) // Create an object for the MyAdapter
        val adapter = MyAdapter(products, this)
        itemList.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val itemDetail = Intent(this, ItemDetail::class.java)
        itemDetail.putExtra("item", products[position])
        startActivity(itemDetail)
    }
}