package com.example.findrestoran.Model

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.findrestoran.Data.DataResto
import com.example.findrestoran.Data.DescRestaurant
import com.example.findrestoran.Data.RestoItem
import com.example.findrestoran.Data.ReviewItem
import com.example.findrestoran.MainActivity
import com.example.findrestoran.NetworkProvider
import com.example.findrestoran.R
import com.google.android.gms.maps.MapView
import kotlinx.android.synthetic.main.detail_restoran.*
import kotlinx.android.synthetic.main.item_row_resto.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailResto : AppCompatActivity() {
    companion object {
        const val ID_RESTO = "id_restaurant"
        const val ID_REST = " "
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_restoran)

        val bundle = intent.getParcelableExtra<DescRestaurant>(ID_RESTO)
        if (bundle != null) {
            val resto_name = findViewById<TextView>(R.id.resto_namerev)
            resto_name.text = bundle.name
            val resto_image = findViewById<ImageView>(R.id.detail_food_img_rev)
            Glide.with(this)
                .load(bundle.featured_image)
                .centerCrop()
                .apply(RequestOptions().override(289, 150))
                .into(resto_image)
            val id_res = bundle.id
            println(id_res)
            val range_harga = findViewById<TextView>(R.id.rangeharga)
            range_harga.text = bundle.currency + bundle.average_cost_for_two + " / " + bundle.price_range + " people"
            val timing = findViewById<TextView>(R.id.bukatoko)
            timing.text = bundle.timings
            val address = findViewById<TextView>(R.id.alamat)
            address.text = bundle.location.address
            val pointReview = findViewById<TextView>(R.id.sumbintang)
            pointReview.text = bundle.UserRating.aggregate_rating
            val starReview = findViewById<RatingBar>(R.id.ratingbar)
            starReview.rating = bundle.UserRating.aggregate_rating.toFloat()
            val allReview = findViewById<TextView>(R.id.review)
            allReview.text = "(" + bundle.UserRating.votes + ")"
//            val coordinate = findViewById<MapView>(R.id.mapView2)
//            coordinate.add
        }
        val dataReview = NetworkProvider.providesHttpAdapter().create(DataResto::class.java)
        dataReview.discoverRestaurantFromReview().enqueue(object : Callback<ReviewItem>{
            override fun onResponse(call: Call<ReviewItem>, response: Response<ReviewItem>) {
                val results = response.body()?.UserReview
                val itemAdapter = findViewById<RecyclerView>(R.id.RestoranRev)
                itemAdapter.addItemDecoration(DividerItemDecoration(this@DetailResto, DividerItemDecoration.VERTICAL))
                itemAdapter.adapter = ReviewAdapter(results ?: emptyList())
            }

            override fun onFailure(call: Call<ReviewItem>, t: Throwable) {
                Log.e(DetailResto::class.java.simpleName, "${t.printStackTrace()}")
            }
        })

    }
}