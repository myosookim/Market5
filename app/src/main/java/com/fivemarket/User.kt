package com.fivemarket

data class User( //사용자 정보를 담을 class
    var name: String,
    var email: String,
    var uId: String
){
    constructor(): this("","","") //이 data 클래스에 사용자 정보가 들어간다.
}

