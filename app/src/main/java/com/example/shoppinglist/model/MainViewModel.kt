package com.example.shoppinglist.model

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val shoppingItems: MutableList<ShoppingItem> = mutableListOf(
        ShoppingItem("Chocolate")
    )

    fun addShoppingItem(item: ShoppingItem) {
        shoppingItems.add(item)
    }

    fun removeShoppingItem(position: Int) {
        shoppingItems.removeAt(position)
    }
}