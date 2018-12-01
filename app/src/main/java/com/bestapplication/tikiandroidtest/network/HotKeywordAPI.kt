package com.bestapplication.tikiandroidtest.network

import io.reactivex.Single
import retrofit2.http.GET

interface HotKeywordAPI {
    @GET("/talenguyen/38b790795722e7d7b1b5db051c5786e5/raw/63380022f5f0c9a100f51a1e30887ca494c3326e/keywords.json")
    fun getHotKeywords(): Single<List<String>>
}