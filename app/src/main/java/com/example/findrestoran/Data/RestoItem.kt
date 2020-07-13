package com.example.findrestoran.Data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestoItem(
    @SerializedName("restaurants")
    val cities: List<listRestaurants>
) : Parcelable

@Parcelize
data class listRestaurants(
    @SerializedName("restaurant")
    val descRestaurant: DescRestaurant
) : Parcelable

@Parcelize
data class DescRestaurant(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("timings")
    val timings: String,
    @SerializedName("average_cost_for_two")
    val average_cost_for_two: String,
    @SerializedName("price_range")
    val price_range: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("user_rating")
    val UserRating: UserRating,

    @SerializedName("featured_image")
    val featured_image: String,
    @SerializedName("has_online_delivery")
    val has_online_delivery: String
) : Parcelable

@Parcelize
data class Location(
    @SerializedName("address")
    val address: String,
    @SerializedName("locality")
    val locality: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
) : Parcelable

@Parcelize
data class UserRating(
    @SerializedName("aggregate_rating")
    val aggregate_rating: String,
    @SerializedName("votes")
    val votes: String
) : Parcelable
