package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistLeatherBinding
import com.fivemarket.viewmodel.ItemViewModel

// 가죽 카테고리 목록 프래그먼트!!
class Itemlist_leatherFragment : Fragment() {

    var binding: FragmentItemlistLeatherBinding? = null
    private val itemViewModel by activityViewModels<ItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemlistLeatherBinding.inflate(layoutInflater)
        binding?.recItemsLeather?.layoutManager = LinearLayoutManager(context)
        binding?.recItemsLeather?.adapter = ItemsAdapter(itemViewModel.items_leather)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel.mitems_leather.observe(viewLifecycleOwner, Observer {
            binding?.recItemsLeather?.post(Runnable { it.filter { x -> x.isLiked } })
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}