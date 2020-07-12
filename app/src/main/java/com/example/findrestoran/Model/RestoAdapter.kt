package com.example.findrestoran.Model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.findrestoran.Data.listRestaurants
import com.example.findrestoran.R
import kotlinx.android.synthetic.main.item_row_resto.view.*

class RestoAdapter(private val result: List<listRestaurants>) : RecyclerView.Adapter<RestoAdapter.CardViewViewHolder>() {

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(restaurant: listRestaurants) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(restaurant.descRestaurant.thumb)
                    .apply(RequestOptions().override(100, 100))
                    .into(img_item_photo)
                tv_restaurant.text = restaurant.descRestaurant.name
                tv_range.text = restaurant.descRestaurant.currency + " " + restaurant.descRestaurant.average_cost_for_two
                tv_rating.text= restaurant.descRestaurant.UserRating.aggregate_rating
                rt_bar.rating = restaurant.descRestaurant.UserRating.aggregate_rating.toFloat()
                totalrt.text = "(" + restaurant.descRestaurant.UserRating.votes + ")"
                status.text = if(restaurant.descRestaurant.has_online_delivery=="0"){
                checkbox.isChecked = false
                    "Offline"+ "\n" + "Only"
                } else {
                    "Online"
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestoAdapter.CardViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_resto, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(result[holder.adapterPosition])
    }
}

