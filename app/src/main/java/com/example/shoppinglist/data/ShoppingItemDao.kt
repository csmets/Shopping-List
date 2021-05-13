package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addShoppingItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_table ORDER BY id ASC")
    fun readAllData(): LiveData<MutableList<ShoppingItem>>

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)
}