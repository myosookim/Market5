package com.fivemarket

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistSilkBinding
import com.fivemarket.databinding.FragmentItemlistTotalBinding
import com.fivemarket.viewmodel.ItemViewModel

// 전체 목록 프래그먼트!!
class Itemlist_totalFragment : Fragment() {

    var binding : FragmentItemlistTotalBinding? = null
    private val itemViewModel by activityViewModels<ItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemlistTotalBinding.inflate(layoutInflater)
        binding?.recItems?.layoutManager = LinearLayoutManager(context)
        binding?.recItems?.adapter = ItemsAdapter(itemViewModel.totalitems,this)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel.mtotalitems.observe(viewLifecycleOwner,{
            (binding?.recItems?.adapter as ItemsAdapter).setData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}