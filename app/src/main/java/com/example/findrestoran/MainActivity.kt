package com.example.findrestoran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.findrestoran.Data.RestoItem
import com.example.findrestoran.Data.DataResto
import com.example.findrestoran.Model.RestoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar = findViewById<ProgressBar>(R.id.pb_home)

        val datasource = NetworkProvider.providesHttpAdapter().create(DataResto::class.java)
        datasource.discoverRestaurantFromSearch().enqueue(object : Callback<RestoItem> {
            override fun onResponse(call: Call<RestoItem>, response: Response<RestoItem>) {
                progressBar.visibility = View.GONE
                val results = response.body()?.cities
                val itemAdapter = findViewById<RecyclerView>(R.id.rv_main)
                itemAdapter.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
                itemAdapter.adapter = RestoAdapter(results ?: emptyList())
            }

            override fun onFailure(call: Call<RestoItem>, t: Throwable) {
                Log.e(MainActivity::class.java.simpleName, "${t.printStackTrace()}")
            }
        })

    }
}
