package com.example.shoppinglist.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.ShoppingDatabase
import com.example.shoppinglist.data.ShoppingItem
import com.example.shoppinglist.data.ShoppingItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<ShoppingItem>>
    private val repository: ShoppingItemRepository

    init {
        val shoppingItemDao = ShoppingDatabase.getDatabase(application).shoppingItemDao()
        repository = ShoppingItemRepository(shoppingItemDao)
        readAllData = repository.readAllData
    }

    fun addShoppingItem(item: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addShoppingItem(item)
        }
    }

    fun removeShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeShoppingItem(shoppingItem)
        }
    }
}