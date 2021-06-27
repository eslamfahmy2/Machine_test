package com.example.network.model


import com.google.gson.annotations.SerializedName


data class ArticleModel(
    @SerializedName("title")
    val title: String,

    @SerializedName("des")
    val des: String,

    @SerializedName("published_date")
    val published_date: String,

    @SerializedName("byline")
    val byline: String,

    @SerializedName("section")
    val section: String,

    @SerializedName("abstract")
    val abstract: String,

    @SerializedName("media")
    private val media: List<MediaModel>,

    @SerializedName("des_facet")
    val desFacet: List<String>,


    ) {

    fun getWith(): Int {
        return try {
            this.media.get(0).mediaMetadata.get(0).width;
        } catch (e: Exception) {
            return 60;
        }
    }

    fun getHeight(): Int {
        return try {
            this.media[0].mediaMetadata[0].height;
        } catch (e: Exception) {
            return 60;
        }
    }

    fun getImage(): String? {
        return try {
            this.media[0].mediaMetadata[0].url;
        } catch (e: Exception) {
            return null;
        }
    }

}
