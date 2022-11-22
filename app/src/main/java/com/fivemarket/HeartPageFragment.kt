package com.fivemarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fivemarket.databinding.FragmentHeartPageBinding
import com.fivemarket.databinding.FragmentItemlistCottonBinding

// 찜 목록 프래그먼트!!
// 레이아웃 파일의 fragment_heart_page.xml과 함께 찜 기능 구현할 것

class HeartPageFragment : Fragment() {

    var binding : FragmentHeartPageBinding? = null
    var heart_list : ArrayList<Items> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        for(i in MainActivity().items){
            if(i.isLiked){
                heart_list += i
            }
        }
        binding = FragmentHeartPageBinding.inflate(layoutInflater)
        binding?.recHeart?.layoutManager = LinearLayoutManager(context)
        binding?.recHeart?.adapter = ItemsAdapter(heart_list)
        return binding?.root
    }

}