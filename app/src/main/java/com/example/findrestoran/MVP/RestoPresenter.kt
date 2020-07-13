package com.example.findrestoran.MVP

import com.example.findrestoran.Data.DataResto
import com.example.findrestoran.Data.RestoItem
import com.example.findrestoran.NetworkProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestoPresenter(private val view: RestoView) {

    fun discoverResto() {
        view.OnShowLoading()

        val dataSource = NetworkProvider.providesHttpAdapter().create(DataResto::class.java)

        dataSource.discoverRestaurantFromSearch().enqueue(object : Callback<RestoItem>{
            override fun onResponse(call: Call<RestoItem>, response: Response<RestoItem>) {
                view.OnHideLoading()
                view.OnResponse(response.body()?.cities ?: emptyList())
            }

            override fun onFailure(call: Call<RestoItem>, t: Throwable) {
                view.OnHideLoading()
                view.OnFailure(t)
            }
        })
    }
}