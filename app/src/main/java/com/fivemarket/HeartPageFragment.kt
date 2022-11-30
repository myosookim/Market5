package com.fivemarket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentHeartPageBinding
import com.fivemarket.viewmodel.ItemViewModel

// 찜 목록 프래그먼트!!
// 레이아웃 파일의 fragment_heart_page.xml과 함께 찜 기능 구현할 것
class HeartPageFragment : Fragment() {

    private var binding: FragmentHeartPageBinding? = null
    private val itemViewModel by activityViewModels<ItemViewModel>()
    var items : MutableLiveData<ArrayList<Items>> = itemViewModel.heartlist

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeartPageBinding.inflate(layoutInflater)
        binding?.recHeart?.layoutManager = LinearLayoutManager(context)
        binding?.recHeart?.adapter = ItemsAdapter(itemViewModel.heartlist.value!!)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemViewModel.heartlist.observe(viewLifecycleOwner){
            items.value = it
            binding?.recHeart?.adapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}