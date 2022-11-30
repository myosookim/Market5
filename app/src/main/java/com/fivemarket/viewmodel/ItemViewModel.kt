package com.fivemarket.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fivemarket.Etype
import com.fivemarket.Items
import com.fivemarket.R
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {

    // ArrayList<Items>형식 데이터들 (MutableLiveData의 value로 넣을 것들)
    var items_silk = arrayListOf(
        Items("실크","파이브원단",4000, Etype.SILK, R.drawable.main_itemimg1,false),
        Items("부드러운 실크","파이브원단",4000, Etype.SILK, R.drawable.main_itemimg1,false),
        Items("더 부드러운 실크","파이브원단",4000, Etype.SILK, R.drawable.main_itemimg1,false)
    )
    var items_cotton = arrayListOf(
        Items("난색 면","파이브원단",5000, Etype.COTTON, R.drawable.main_itemimg2,false),
        Items("한색 면","면 사랑방",6000, Etype.COTTON, R.drawable.main_itemimg2,false)
    )
    var items_leather = arrayListOf(
        Items("인조 가죽","가죽공방",8000, Etype.LEATHER, R.drawable.leather_black,false),
        Items("얇은 가죽","가죽공방",8000, Etype.LEATHER, R.drawable.leather_black,false),
        Items("두꺼운 가죽","가죽공방",9000, Etype.LEATHER, R.drawable.leather_black,false),
        Items("천연 가죽","빈티지공방",10000, Etype.LEATHER, R.drawable.leather_black,false)
    )
    var items_lace = arrayListOf(
        Items("저렴한 레이스","진희네원단",3000, Etype.LACE, R.drawable.main_itemimg3,false),
        Items("자수 레이스","하늘하늘",4000, Etype.LACE, R.drawable.main_itemimg3,false),
        Items("랏셀 레이스","하늘하늘",4000, Etype.LACE, R.drawable.main_itemimg3,false),
        Items("토션 레이스","하늘하늘",5000, Etype.LACE, R.drawable.main_itemimg3,false),
        Items("코멕스 레이스","코지라이프",6000, Etype.LACE, R.drawable.main_itemimg3,false),
        Items("망사 레이스","코지라이프",6000, Etype.LACE, R.drawable.main_itemimg3,false)
    )


    // MutableLiveData 형식 데이터들. 실질적으로 쓰일 것
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

    // Livedata들을 합쳐 관리할 수 있는 MediatorLiveData를 선언.
    // 각 카테고리의 모든 데이터를 더한 전체 목록으로 쓰임
    val totalitems :MediatorLiveData<ArrayList<Items>> = MediatorLiveData()
    init {
        totalitems.addSource(mitems_silk){
            totalitems.value = mitems_silk.value
        }
        totalitems.addSource(mitems_cotton){
            totalitems.value = mitems_cotton.value
        }
        totalitems.addSource(mitems_leather){
            totalitems.value = mitems_silk.value
        }
        totalitems.addSource(mitems_lace){
            totalitems.value = mitems_lace.value
        }
    }

    val heartlist = MutableLiveData<ArrayList<Items>>()

    fun setData(items: ArrayList<Items>) {
        heartlist.value = items.filter { x -> x.isLiked } as ArrayList<Items>
        totalitems.value = items
    }


}