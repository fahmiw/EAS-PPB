package com.example.findrestoran.MVP

import com.example.findrestoran.Data.listRestaurants


interface RestoView {
    fun OnShowLoading()
    fun OnHideLoading()
    fun OnResponse(results: List<listRestaurants>)
    fun OnFailure(error: Throwable)
}