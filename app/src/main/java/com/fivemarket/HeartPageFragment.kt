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
    var heart_list : ArrayList<Items> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        for(i in itemViewModel.items){
            if(i.isLiked){
                heart_list += i
                Log.e("${i.name}","찜 목록에 추가됩니다.")
            }
            Log.e("for문","돌았음.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeartPageBinding.inflate(layoutInflater)
        binding?.recHeart?.layoutManager = LinearLayoutManager(context)
        binding?.recHeart?.adapter = ItemsAdapter(heart_list)
        return binding?.root
    }

}