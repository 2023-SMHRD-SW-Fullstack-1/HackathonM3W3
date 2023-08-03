package com.hjy.hackathon.vo

data class ChatRoomVO(
    var uidOne: String, // 상대방 아이디
    var uidTwo: String, // 로그인한 아이디
    var lastChatMsg: String,
    var lastChatTime: String,
) {
    constructor(): this("", "", "", "")
}
