package com.example.shoppinglist.model

data class ShoppingItem(
    val name: String,
    val collected: Boolean = false,
    val quantity: Number = 1
)