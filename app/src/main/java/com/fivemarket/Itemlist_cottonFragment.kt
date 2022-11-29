package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentItemlistCottonBinding
import com.fivemarket.viewmodel.ItemViewModel

// 면 카테고리 목록 프래그먼트!!
class Itemlist_cottonFragment : Fragment() {

    var binding: FragmentItemlistCottonBinding? = null
    private val itemViewModel by activityViewModels<ItemViewModel>()
    private var items : MutableLiveData<ArrayList<Items>> = itemViewModel.mitems_cotton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemlistCottonBinding.inflate(layoutInflater)
        binding?.recItemsCotton?.layoutManager = LinearLayoutManager(context)
        binding?.recItemsCotton?.adapter = ItemsAdapter(items)
        itemViewModel.mitems_cotton.observe(viewLifecycleOwner){
            items.value = it
            binding?.recItemsCotton?.adapter?.notifyDataSetChanged()
        }
        return binding?.root
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}