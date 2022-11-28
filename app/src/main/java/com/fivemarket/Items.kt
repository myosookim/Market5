package com.fivemarket

enum class Etype{   // 실크, 면, 가죽 , 레이스
    SILK,
    COTTON,
    LEATHER,
    LACE
}

class Items(
    val name:String,    // 상품 이름
    val co_name:String,  // 상품 만든 업체 이름
    val price:Int,      // 가격
    val type:Etype,       // 종류 ( 실크, 면, 가죽 , 레이스 )
    val img:Int,         // 상품 이미지
    var isLiked:Boolean
)