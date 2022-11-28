package com.fivemarket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentHeartPageBinding
import com.fivemarket.viewmodel.ItemViewModel

// 찜 목록 프래그먼트!!
// 레이아웃 파일의 fragment_heart_page.xml과 함께 찜 기능 구현할 것

class HeartPageFragment : Fragment() {

    private val itemViewModel by activityViewModels<ItemViewModel>()

    var binding : FragmentHeartPageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeartPageBinding.inflate(layoutInflater)
        val items: ArrayList<Items> = itemViewModel.heartlist
        binding?.recHeart?.layoutManager = LinearLayoutManager(context)
        binding?.recHeart?.adapter = ItemsAdapter(items)
        return binding?.root
    }

}