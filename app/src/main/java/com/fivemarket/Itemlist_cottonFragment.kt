package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistCottonBinding


class Itemlist_cottonFragment : Fragment() {

    var binding: FragmentItemlistCottonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var items: ArrayList<Items> = requireActivity().intent!!.extras!!.get("cotton_items") as ArrayList<Items>
        binding = FragmentItemlistCottonBinding.inflate(layoutInflater)
        binding?.recItemsCotton?.layoutManager = LinearLayoutManager(context)
        binding?.recItemsCotton?.adapter = ItemsAdapter(items)
        return binding?.root
    }

}