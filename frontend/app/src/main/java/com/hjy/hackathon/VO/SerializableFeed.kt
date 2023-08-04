package com.hjy.hackathon.vo

import java.io.Serializable

data class SerializableFeed(
    val id : String?,
    val profile : String?,
    val img : String?,
    val nick : String?,
    val content : String?,
    val cost : String?,
    val category : String?
) : Serializable
