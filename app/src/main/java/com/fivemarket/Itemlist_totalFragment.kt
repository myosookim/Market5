package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistSilkBinding
import com.fivemarket.databinding.FragmentItemlistTotalBinding
import com.fivemarket.viewmodel.ItemViewModel

// 전체 목록 프래그먼트!!
class Itemlist_totalFragment : Fragment() {

    var binding : FragmentItemlistTotalBinding? = null
    private val itemViewModel by activityViewModels<ItemViewModel>()
    private var items : MutableLiveData<ArrayList<Items>> = itemViewModel.mitems_total

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemlistTotalBinding.inflate(layoutInflater)
        binding?.recItems?.layoutManager = LinearLayoutManager(context)
        binding?.recItems?.adapter = ItemsAdapter(items)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel.mitems_total.observe(viewLifecycleOwner){
            items.value = it
            binding?.recItems?.adapter?.notifyDataSetChanged()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}