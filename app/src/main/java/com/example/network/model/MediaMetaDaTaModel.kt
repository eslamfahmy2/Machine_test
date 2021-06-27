package com.example.network.model

import com.google.gson.annotations.SerializedName

data class MediaMetaDaTaModel(

    @SerializedName("url")
    val url: String,

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int,

    )
