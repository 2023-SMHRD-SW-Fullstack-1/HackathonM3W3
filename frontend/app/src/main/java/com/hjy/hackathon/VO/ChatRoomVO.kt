package com.hjy.hackathon.vo

data class ChatRoomVO(
    var uidOne: String,
    var uidTwo: String,
    var lastChatMsg: String,
    var lastChatTime: String,
) {
    constructor(): this("", "", "", "")
}
