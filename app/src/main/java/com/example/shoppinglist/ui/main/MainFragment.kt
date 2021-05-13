package com.example.shoppinglist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.MainFragmentBinding
import com.example.shoppinglist.model.MainViewModel
import com.example.shoppinglist.model.ShoppingItemAdapter

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    private val viewModel: MainViewModel by activityViewModels()

    private val itemTouchHelper by lazy {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {

            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                val adapter = recyclerView.adapter as ShoppingItemAdapter
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                adapter.moveItem(from, to)
                adapter.notifyItemMoved(from, to)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }
        }

        ItemTouchHelper(simpleItemTouchCallback)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?)
    : View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = ShoppingItemAdapter(viewModel)
        binding.shoppingListView.adapter = adapter
        binding.shoppingListView.setHasFixedSize(true)
        itemTouchHelper.attachToRecyclerView(binding.shoppingListView)

        viewModel.readAllData.observe(viewLifecycleOwner, Observer { shoppingItem ->
            adapter.setData(shoppingItem)
        })

        binding.addItemViewBtn.setOnClickListener { addItemView() }

        return binding.root
    }

    private fun addItemView() {
        findNavController().navigate(R.id.action_mainFragment_to_addItemFragment)
    }

}