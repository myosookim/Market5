package com.fivemarket

data class Message( //메세지를 담을 변수와 아이디를 담을 변수
    var message: String?,
    var sendId: String?
){
    constructor():this("","")
}
