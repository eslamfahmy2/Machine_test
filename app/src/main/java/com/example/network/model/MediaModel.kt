package com.example.network.model


import com.google.gson.annotations.SerializedName

data class MediaModel(

    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetaDaTaModel>
)
