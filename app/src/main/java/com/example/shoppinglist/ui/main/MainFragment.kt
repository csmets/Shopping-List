package com.example.shoppinglist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.MainFragmentBinding
import com.example.shoppinglist.model.MainViewModel
import com.example.shoppinglist.model.ShoppingItemAdapter

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    private val viewModel: MainViewModel by activityViewModels()

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

        binding.shoppingListView.adapter = ShoppingItemAdapter(viewModel)
        binding.shoppingListView.setHasFixedSize(true)

        binding.addItemViewBtn.setOnClickListener { addItemView() }

        return binding.root
    }

    private fun addItemView() {
        findNavController().navigate(R.id.action_mainFragment_to_addItemFragment)
    }

}