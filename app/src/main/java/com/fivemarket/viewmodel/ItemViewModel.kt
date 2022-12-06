package com.fivemarket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fivemarket.Etype
import com.fivemarket.Items
import com.fivemarket.R

class ItemViewModel : ViewModel() {

    // ArrayList<Items>형식 데이터들 (MutableLiveData의 value로 넣을 것들)
    var items_silk = arrayListOf(
        Items("하얀 실크","파이브원단",3000, Etype.SILK, R.drawable.silk_white,false),
        Items("분홍 실크","파이브원단",4000, Etype.SILK, R.drawable.silk_pink,false),
        Items("회색 실크","하늘하늘",4000, Etype.SILK, R.drawable.silk_gray,false),
        Items("보라색 실크","하늘하늘",4000, Etype.SILK, R.drawable.silk_purple,false),
        Items("섬세한 화이트 실크","파이브원단",5000, Etype.SILK, R.drawable.silk_white_gorgeous,false),
        Items("섬세한 초록색 실크","파이브원단",5000, Etype.SILK, R.drawable.silk_gorgeous_green,false)
    )
    var items_cotton = arrayListOf(
        Items("20수 면 도트","면 사랑방",5000, Etype.COTTON, R.drawable.cotton_dot,false),
        Items("20수 면 3color","파이브원단",6000, Etype.COTTON, R.drawable.cotton_3color,false),
        Items("30수 면 아이리스","파이브원단",5000, Etype.COTTON, R.drawable.cotton_iris,false),
        Items("20수 면 코스모스","파이브원단",5000, Etype.COTTON, R.drawable.cotton_cosmos,false)
    )
    var items_leather = arrayListOf(
        Items("인조 가죽","가죽공방",8000, Etype.LEATHER, R.drawable.leather_black,false),
        Items("얇은 가죽","가죽공방",8000, Etype.LEATHER, R.drawable.leather_black,false),
        Items("두꺼운 가죽","가죽공방",9000, Etype.LEATHER, R.drawable.leather_black,false),
        Items("천연 가죽","빈티지공방",10000, Etype.LEATHER, R.drawable.leather_black,false)
    )
    var items_lace = arrayListOf(
        Items("저렴한 레이스","진희네원단",3000, Etype.LACE, R.drawable.lace_cheap,false),
        Items("꽃 자수 레이스","진희네 원단",4000, Etype.LACE, R.drawable.lace_flower,false),
        Items("그린 레이스","하늘하늘",4000, Etype.LACE, R.drawable.lace_green,false),
        Items("토션 레이스","하늘하늘",5000, Etype.LACE, R.drawable.lace_torsion,false),
        Items("랏셀 레이스","보랏빛밤",5000, Etype.LACE, R.drawable.lace_raschel_blossom,false),
        Items("코멕스 레이스","코지라이프",8000, Etype.LACE, R.drawable.main_itemimg3,false),
        Items("망사 레이스","코지라이프",10000, Etype.LACE, R.drawable.lace_net,false)
    )
    var totalitems = arrayListOf<Items>()
    init {
        totalitems += items_silk + items_cotton + items_leather + items_lace
    }

    // MutableLiveData 형식 데이터들.
    val mitems_silk = MutableLiveData<ArrayList<Items>>()
    val mitems_cotton = MutableLiveData<ArrayList<Items>>()
    val mitems_leather = MutableLiveData<ArrayList<Items>>()
    val mitems_lace = MutableLiveData<ArrayList<Items>>()
    init {
        mitems_silk.value = items_silk
        mitems_cotton.value = items_cotton
        mitems_leather.value = items_leather
        mitems_lace.value = items_lace
    }

    val mtotalitems :MutableLiveData<ArrayList<Items>> = MutableLiveData()
    init {
        mtotalitems.value = totalitems
    }


    /* 안 쓰는 코드. 삭제해도 됩니다

    val heartlist = ArrayList<Items>()
    val mheartlist = MutableLiveData<ArrayList<Items>>()
    fun setData(items: ArrayList<Items>) {
        mheartlist.value = items.filter { x -> x.isLiked } as ArrayList<Items>
        mtotalitems.value = items
    }

    fun addHeartedItems(items : Items){
        heartlist.add(items)
        mheartlist.value = heartlist
    }

    fun deleteHeartedItems(items: Items){
        heartlist.remove(items)
        mheartlist.value = heartlist
    }
     */

}