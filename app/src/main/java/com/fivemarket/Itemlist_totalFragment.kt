package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.inflate
import com.fivemarket.databinding.FragmentItemlistTotalBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Itemlist_totalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Itemlist_totalFragment : Fragment() {

    var binding : FragmentItemlistTotalBinding? = null

    val items = arrayOf(

        Items("상품1","업체1",5000,Etype.SILK,R.drawable.main_itemimg1),
        Items("상품2","업체2",6000,Etype.LEATHER,R.drawable.main_itemimg2),
        Items("상품3","업체3",7000,Etype.LACE,R.drawable.main_itemimg3)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inflate the layout for this fragment
        binding = FragmentItemlistTotalBinding.inflate(LayoutInflater.from(context))
        binding?.recItems?.layoutManager = LinearLayoutManager(context)
        binding?.recItems?.adapter = ItemsAdapter(items)

    }

}