package com.example.shoppinglist.data

import androidx.lifecycle.LiveData

class ShoppingItemRepository(private val shoppingItemDao: ShoppingItemDao) {

    val readAllData: LiveData<MutableList<ShoppingItem>> = shoppingItemDao.readAllData()

    suspend fun addShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItemDao.addShoppingItem(shoppingItem)
    }

    suspend fun removeShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItemDao.deleteShoppingItem(shoppingItem)
    }
}