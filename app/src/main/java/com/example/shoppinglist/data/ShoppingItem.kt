package com.example.shoppinglist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_table")
data class ShoppingItem(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val name: String,
        val collected: Boolean,
        val quantity: Int
)