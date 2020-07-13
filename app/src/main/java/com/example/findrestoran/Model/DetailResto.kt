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
import com.example.findrestoran.Data.ReviewItem
import com.example.findrestoran.Data.review
import com.example.findrestoran.NetworkProvider
import com.example.findrestoran.R
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailResto : AppCompatActivity(), OnMapReadyCallback, MapboxMap.OnMapClickListener {
    companion object {
        const val ID_RESTO = "id_restaurant"
    }

    val mapView: MapView? = null
    var mapboxMap: MapboxMap? = null
    var data: DescRestaurant? = null

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

            // Maps
            Mapbox.getInstance(this, "pk.eyJ1IjoiYW5kaWZhdXp5NyIsImEiOiJja2Npa2o4Y2Uwa3ZhMnRvZTY4YjUwOTBuIn0.V2U0Cys-qY9IIoSCVFHu4g")
            val mapView = findViewById(R.id.mapView2) as MapView
            mapView.onCreate(savedInstanceState)
            mapView.getMapAsync(this)
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
    override fun onMapReady(mapboxMap: MapboxMap?) {
        this.mapboxMap = mapboxMap
        mapboxMap?.addOnMapClickListener(this)
    }

    override fun onMapClick(point: LatLng) {
        val position = CameraPosition.Builder()
            .target(LatLng(data!!.location.latitude.toDouble(), data!!.location.longitude.toDouble()))
            .zoom(17.0) // Sets the zoom
            .bearing(180.0) // Rotate the camera
            .tilt(30.0) // Set the camera tilt
            .build()
        mapboxMap?.animateCamera(
            CameraUpdateFactory
                .newCameraPosition(position), 7000)
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapboxMap?.removeOnMapClickListener(this)
        mapView?.onDestroy()
    }

}



