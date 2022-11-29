package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistLeatherBinding
import com.fivemarket.viewmodel.ItemViewModel

// 가죽 카테고리 목록 프래그먼트!!
class Itemlist_leatherFragment : Fragment() {

    var binding: FragmentItemlistLeatherBinding? = null
    private val itemViewModel by activityViewModels<ItemViewModel>()
    private var items : MutableLiveData<ArrayList<Items>> = itemViewModel.mitems_leather

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemlistLeatherBinding.inflate(layoutInflater)
        binding?.recItemsLeather?.layoutManager = LinearLayoutManager(context)
        binding?.recItemsLeather?.adapter = ItemsAdapter(items)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel.mitems_leather.observe(viewLifecycleOwner){
            items.value = it
            binding?.recItemsLeather?.adapter?.notifyDataSetChanged()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}