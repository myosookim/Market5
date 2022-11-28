package com.fivemarket

import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {


    var items_silk = arrayListOf(
        Items("실크","파이브원단",4000,Etype.SILK,R.drawable.main_itemimg1,false),
        Items("부드러운 실크","파이브원단",4000,Etype.SILK,R.drawable.main_itemimg1,false),
        Items("더 부드러운 실크","파이브원단",4000,Etype.SILK,R.drawable.main_itemimg1,false))
    var items_cotton = arrayListOf(
        Items("난색 면","파이브원단",5000,Etype.COTTON,R.drawable.main_itemimg2,false),
        Items("한색 면","면 사랑방",6000,Etype.COTTON,R.drawable.main_itemimg2,false))
    var items_leather = arrayListOf(
        Items("인조 가죽","가죽공방",8000,Etype.LEATHER,R.drawable.leather_black,false),
        Items("얇은 가죽","가죽공방",8000,Etype.LEATHER,R.drawable.leather_black,false),
        Items("두꺼운 가죽","가죽공방",9000,Etype.LEATHER,R.drawable.leather_black,false),
        Items("천연 가죽","빈티지공방",10000,Etype.LEATHER,R.drawable.leather_black,false))
    var items_lace = arrayListOf(
        Items("저렴한 레이스","진희네원단",3000,Etype.LACE,R.drawable.main_itemimg3,false),
        Items("자수 레이스","하늘하늘",4000,Etype.LACE,R.drawable.main_itemimg3,false),
        Items("랏셀 레이스","하늘하늘",4000,Etype.LACE,R.drawable.main_itemimg3,false),
        Items("토션 레이스","하늘하늘",5000,Etype.LACE,R.drawable.main_itemimg3,false),
        Items("코멕스 레이스","코지라이프",6000,Etype.LACE,R.drawable.main_itemimg3,false),
        Items("망사 레이스","코지라이프",6000,Etype.LACE,R.drawable.main_itemimg3,false))

    var items = arrayListOf<Items>().apply {
        addAll(items_silk)
        addAll(items_cotton)
        addAll(items_leather)
        addAll(items_lace)
    }

}