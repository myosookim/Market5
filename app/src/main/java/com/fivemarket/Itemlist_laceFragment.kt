package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistLaceBinding

class Itemlist_laceFragment : Fragment() {
    var binding: FragmentItemlistLaceBinding? = null

    private val itemViewModel by activityViewModels<ItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var items: ArrayList<Items> = itemViewModel.items_lace
        binding = FragmentItemlistLaceBinding.inflate(layoutInflater)
        binding?.recItemsLace?.layoutManager = LinearLayoutManager(context)
        binding?.recItemsLace?.adapter = ItemsAdapter(items)
        return binding?.root
    }
}