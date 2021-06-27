package com.example.network

import com.example.network.model.ResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {

    @GET("all-sections/7.json?api-key=kjWL3IMsOTCUgfZWnsq9HJix6bGqMjGS")
    suspend fun list(
        @Header("app-id") appID: String,
        @Header("Content-Type") content: String,
        @Header("Lang") lang: String,
        @Header("Device-type") device: String
    ): ResponseModel

    @GET("all-sections/7.json?api-key=kjWL3IMsOTCUgfZWnsq9HJix6bGqMjGS")
    fun listRX(
        @Header("app-id") appID: String,
        @Header("Content-Type") content: String,
        @Header("Lang") lang: String,
        @Header("Device-type") device: String
    ): Deferred<ResponseModel>


}