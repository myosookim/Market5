package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistCottonBinding

class Itemlist_cottonFragment : Fragment() {

    var binding: FragmentItemlistCottonBinding? = null

    private val itemViewModel by activityViewModels<ItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val items: ArrayList<Items> = itemViewModel.items_cotton
        binding = FragmentItemlistCottonBinding.inflate(layoutInflater)
        binding?.recItemsCotton?.layoutManager = LinearLayoutManager(context)
        binding?.recItemsCotton?.adapter = ItemsAdapter(items)
        return binding?.root
    }

}