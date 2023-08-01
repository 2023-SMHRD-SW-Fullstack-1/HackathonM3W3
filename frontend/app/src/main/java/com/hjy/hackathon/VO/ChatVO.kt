package com.hjy.hackathon.vo

data class ChatVO(  var uid : String, var msg : String,  var time : String){
    constructor() : this("","","")
}
