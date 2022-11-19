package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistLeatherBinding


class Itemlist_leatherFragment : Fragment() {

    var binding: FragmentItemlistLeatherBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var items: ArrayList<Items> = requireActivity().intent!!.extras!!.get("leather_items") as ArrayList<Items>
        binding = FragmentItemlistLeatherBinding.inflate(layoutInflater)
        binding?.recItemsLeather?.layoutManager = LinearLayoutManager(context)
        binding?.recItemsLeather?.adapter = ItemsAdapter(items)
        return binding?.root
    }


}