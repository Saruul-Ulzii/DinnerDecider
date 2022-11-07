package com.sw06d120.lesson6_quizandrecycler

import java.io.Serializable

data class Item(val itemId: Int, val title: String, val price: Double, val color: String, val image: String, val desc: String): Serializable