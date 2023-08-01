package com.hjy.hackathon.vo

data class ChatVO(  var msg : String, var uid : String,  var time : String){
    constructor() : this("","","")
}
