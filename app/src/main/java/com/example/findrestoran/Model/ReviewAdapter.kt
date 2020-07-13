package com.example.findrestoran.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findrestoran.Data.listUserReview
import com.example.findrestoran.Data.review
import com.example.findrestoran.R
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(private val result: List<listUserReview>) : RecyclerView.Adapter<ReviewAdapter.CardViewViewHolder>() {
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(Review: listUserReview) {
            with(itemView){
                tv_rev_name.text = Review.review.user.name
                rt_barrev.rating = Review.review.rating.toFloat()
                comment.text = Review.review.review_text
            }
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewAdapter.CardViewViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: ReviewAdapter.CardViewViewHolder, position: Int) {
        holder.bind(result[holder.adapterPosition])
    }
}