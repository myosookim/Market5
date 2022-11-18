package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.fivemarket.databinding.FragmentItemlistTotalBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Itemlist_totalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Itemlist_totalFragment : Fragment() {

    var binding : FragmentItemlistTotalBinding? = null

    val items = arrayOf(
        Items("상품1","업체1",5000,Etype.SILK,getResources().getDrawable(R.drawable.main_itemimg3, null)),
        Items("상품2","업체2",6000,Etype.LEATHER,getResources().getDrawable(R.drawable.main_itemimg2, null)),
        Items("상품3","업체3",7000,Etype.LACE,getResources().getDrawable(R.drawable.main_itemimg1, null))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemlistTotalBinding.inflate(inflater)
        binding?.recItems?.layoutManager = LinearLayoutManager(context)
        binding?.recItems?.adapter = ItemsAdapter(items)

        return binding?.root // <- 아마 이 부분이 문제일듯...??
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemlistTotalBinding.bind(view)
    }

}