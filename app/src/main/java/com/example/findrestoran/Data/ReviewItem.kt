package com.example.findrestoran.Data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewItem(
    @SerializedName("user_reviews")
    val UserReview: List<listUserReview>
) : Parcelable

@Parcelize
data class listUserReview(
    @SerializedName("review")
    val review: review
) : Parcelable

@Parcelize
data class review(
    @SerializedName("user")
    val user: user,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("review_text")
    val review_text: String
) : Parcelable

@Parcelize
data class user(
    @SerializedName("name")
    val name: String
) : Parcelable