package com.fivemarket

data class User(
    var name: String,
    var email: String,
    var uId: String
){
    constructor(): this("","","") //이 data 클래스에 사용자 정보가 들어간다.
}

