package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistSilkBinding


class Itemlist_silkFragment : Fragment() {

    var binding: FragmentItemlistSilkBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var items: Array<Items> = requireActivity().intent!!.extras!!.get("silk_items") as Array<Items>
        binding = FragmentItemlistSilkBinding.inflate(layoutInflater)
        binding?.recItemsSilk?.layoutManager = LinearLayoutManager(context)
        binding?.recItemsSilk?.adapter = ItemsAdapter(items)
        return binding?.root
    }


}