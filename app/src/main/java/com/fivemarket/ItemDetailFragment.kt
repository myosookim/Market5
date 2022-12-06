package com.fivemarket

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.fivemarket.databinding.FragmentItemDetailBinding

class ItemDetailFragment : Fragment() {
    var binding: FragmentItemDetailBinding? = null
    private var data : Items? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 핸드폰 or 에뮬레이터 SDK 버전에 따라 getSerializable 문법 달라짐.
        // 이유: 33이상부터 else문에 있는 문법이 deprecated되어,
        // 33이상은 if문의 문법, 미만은 else문의 문법 대로 해야함.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){ // 수정하지 마시오
            data = arguments?.getSerializable("item", Items::class.java)
        }
        else{
            data = arguments?.getSerializable("item") as Items
        }

        // bundle로 데이터를 잘 받았는지 확인하기 위한 로그
        Log.e("데이터 $data","${data?.name}")

        //view?.findViewById<TextView>(R.id.item_name)?.setText(data?.name)
        //view?.findViewById<TextView>(R.id.item_corp)?.setText(data?.co_name)

        //binding을 사용했을 때 빈칸 뷰가 나오는 것은 비정상적.
        // adapter에서 이쪽으로 내비게이팅 하면서 View에 문제가 생긴 거 아닐까 추측... 아닐수도
        binding = FragmentItemDetailBinding.inflate(layoutInflater)
        return binding?.root
        // 이것을 쓰면 화면이 나오긴 하지만, 화면에는 이 문법대로 세팅한 데이터값이 적용되지 않음(부동).
        //return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.itemName?.text = data?.name
        binding?.itemCorp?.text = data?.co_name
        binding?.itemImage?.setImageResource(data!!.img)
        binding?.itemPrices?.text = data?.price.toString()
        when(data?.type){
            Etype.SILK ->{
                binding?.itemCategory?.text = "실크 원단"
                // 디자인 방식에 대해 고민중
                binding?.imgFirst?.setImageResource(R.drawable.firstsilk)
                binding?.imgSecond?.setImageResource(R.drawable.secondsilk)
            }
            Etype.COTTON ->{
                binding?.itemCategory?.text = "면 원단"
            }
            Etype.LEATHER ->{
                binding?.itemCategory?.text = "가죽 원단"
            }
            Etype.LACE ->{
                binding?.itemCategory?.text = "레이스 원단"
            }
            else ->{
                binding?.itemCategory?.text = ""
            }
        }
        if(data?.isLiked == true)
            binding?.btnHeartIndetail?.setImageResource(R.drawable.heart_24dp_red)
        else
            binding?.btnHeartIndetail?.setImageResource(R.drawable.heart_24dp_gray)

        binding?.btnHeartIndetail?.setOnClickListener {
            if(data?.isLiked == true){
                binding?.btnHeartIndetail?.setImageResource(R.drawable.heart_24dp_gray)
                data?.isLiked = false
                Log.d("${data?.name}","찜 : ${data?.isLiked}")
            }
            else{
                binding?.btnHeartIndetail?.setImageResource(R.drawable.heart_24dp_red)
                data?.isLiked = true
                Log.d("${data?.name}","찜 : ${data?.isLiked}")
            }
        }
    }

    /*
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        data = args?.getSerializable("key", Items::class.java)
    }
     */

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}