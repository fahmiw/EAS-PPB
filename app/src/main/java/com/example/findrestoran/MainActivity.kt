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
import com.example.findrestoran.Data.listRestaurants
import com.example.findrestoran.MVP.RestoPresenter
import com.example.findrestoran.MVP.RestoView
import com.example.findrestoran.Model.RestoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), RestoView {

    private lateinit var progressBar: ProgressBar
    private lateinit var itemAdaper: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.pb_home)
        val itemAdapter = findViewById<RecyclerView>(R.id.rv_main)
        val presenter = RestoPresenter(this)
        presenter.discoverResto()

    }

    override fun OnShowLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun OnHideLoading() {
        progressBar.visibility = View.GONE
        itemAdaper.visibility = View.VISIBLE
    }

    override fun OnResponse(results: List<listRestaurants>) {
        itemAdaper.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        itemAdaper.adapter = RestoAdapter(results)
    }

    override fun OnFailure(error: Throwable) {
        Log.e(MainActivity::class.java.simpleName, "${error.printStackTrace()}")
    }
}
