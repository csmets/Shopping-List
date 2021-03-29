package com.example.shoppinglist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.model.MainViewModel
import com.example.shoppinglist.model.ShoppingItem

class AddItemFragment: Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val view: View = inflater.inflate(R.layout.add_item_fragment, container, false)

        // Click listener to button to go to main fragment view.
        val cancelButton: Button = view.findViewById(R.id.cancel_item)
        cancelButton.setOnClickListener {
            cancelItem()
        }

        // Click listener to add new shopping item and return to main fragment view
        val addItemButton: Button = view.findViewById(R.id.add_item)
        addItemButton.setOnClickListener {
            addItem(view)
        }

        return view
    }

    private fun cancelItem() {
        findNavController().navigate(R.id.action_addItemFragment_to_mainFragment)
    }

    private fun addItem(view: View) {
        val itemName: EditText = view.findViewById(R.id.item_name_field)
        val quantity: EditText = view.findViewById(R.id.quantity_field)
        val shoppingItem = ShoppingItem(
                name = itemName.text.toString(),
                quantity = quantity.text.toString().toInt()
        )
        viewModel.addShoppingItem(shoppingItem)
        findNavController().navigate(R.id.action_addItemFragment_to_mainFragment)
    }
}