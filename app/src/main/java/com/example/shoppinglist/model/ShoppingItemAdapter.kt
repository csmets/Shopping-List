package com.example.shoppinglist.model

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.ShoppingItem

class ShoppingItemAdapter(
    private val viewModel: MainViewModel
    ) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {

    private var shoppingItemsList: MutableList<ShoppingItem> = mutableListOf()

    class ShoppingItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val quantity: TextView = view.findViewById(R.id.quantity)
        val item: CheckBox = view.findViewById(R.id.item_name)
        val removeItemBtn: Button = view.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ShoppingItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val item: ShoppingItem = shoppingItemsList[position]

        holder.quantity.text = "Quantity: ${item?.quantity}"
        holder.item.text = item.name

        holder.item.setOnCheckedChangeListener { buttonView, isChecked ->  if (isChecked) {
            buttonView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        } else  {
            buttonView.paintFlags = Paint.ANTI_ALIAS_FLAG
        } }

        holder.removeItemBtn.setOnClickListener {
            viewModel.removeShoppingItem(item)
            this.notifyDataSetChanged()
        }
    }

    override fun getItemCount() = shoppingItemsList.size

    fun setData(shoppingItems: MutableList<ShoppingItem>) {
        this.shoppingItemsList = shoppingItems
        this.notifyDataSetChanged()
    }

    fun moveItem(from: Int, to: Int) {
        val fromEmoji = shoppingItemsList[from]
        shoppingItemsList.removeAt(from)
        if (to < from) {
            shoppingItemsList.add(to, fromEmoji)
        } else {
            shoppingItemsList.add(to - 1, fromEmoji)
        }
    }
}