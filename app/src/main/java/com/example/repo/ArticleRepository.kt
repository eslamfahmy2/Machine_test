package com.example.repo

import com.example.network.ApiInterface
import com.example.network.model.ArticleModel
import com.example.network.model.ResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ArticleRepository @Inject constructor(
    private val api: ApiInterface,
    private val appId: String,
    private val contentType: String,
    private val lang: String,
    private val deviceType: String,
) {

    suspend fun listArticle(): List<ArticleModel> = withContext(Dispatchers.IO) {
        return@withContext api.list(appId, contentType, lang, deviceType).results
    }

    suspend fun listArticleRx(): ResponseModel = withContext(Dispatchers.IO) {
        return@withContext api.listRX(appId, contentType, lang, deviceType).await()
    }

}