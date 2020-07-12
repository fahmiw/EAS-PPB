package com.example.findrestoran.Data

import com.example.findrestoran.Data.RestoItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DataResto {
        @Headers("user-key: bd1e7ac36165762c5c0c4ee523255262")
        @GET("search")
        fun discoverRestaurantFromSearch(
            @Query("entity_id")
            q: Int = 280,
            @Query("entity_type")
            entity_type: String = "city"
        ): Call<RestoItem>
}