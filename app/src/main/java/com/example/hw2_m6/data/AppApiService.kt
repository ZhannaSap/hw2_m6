package com.example.hw2_m6.data

import com.example.hw2_m6.data.model.BaseResponse
import com.example.hw2_m6.data.model.Character
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApiService {
    @GET("character")
    suspend fun getCharacters(): Response<BaseResponse<Character>>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int):Response<Character>
}