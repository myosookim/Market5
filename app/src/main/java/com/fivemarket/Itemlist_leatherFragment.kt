package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistLeatherBinding
import com.fivemarket.viewmodel.ItemViewModel


class Itemlist_leatherFragment : Fragment() {

    var binding: FragmentItemlistLeatherBinding? = null

    private val itemViewModel by activityViewModels<ItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val items: ArrayList<Items> = itemViewModel.items_leather
        binding = FragmentItemlistLeatherBinding.inflate(layoutInflater)
        binding?.recItemsLeather?.layoutManager = LinearLayoutManager(context)
        binding?.recItemsLeather?.adapter = ItemsAdapter(items)
        return binding?.root
    }


}