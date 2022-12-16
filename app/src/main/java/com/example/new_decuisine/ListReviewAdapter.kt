package com.example.new_decuisine

import android.content.Intent
import android.media.Rating
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils

class ListReviewAdapter(var reviewList: ArrayList<Review>): RecyclerView.Adapter<ListReviewAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListReviewAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListReviewAdapter.ListViewHolder, position: Int) {
        holder.itemView.apply{
            var namaReview = findViewById<TextView>(R.id.review_name)
            var kategoriReview = findViewById<TextView>(R.id.review_cat)
            var pembuatReview = findViewById<TextView>(R.id.review_creator)
            var isiReview = findViewById<TextView>(R.id.review_review)
            var ratingReview = findViewById<RatingBar>(R.id.review_rating)

            namaReview.text = reviewList[position].name
            kategoriReview.text = reviewList[position].category
            pembuatReview.text = reviewList[position].creator
            isiReview.text = reviewList[position].review
            ratingReview.rating = reviewList[position].rating.toFloat()
        }
    }

    override fun getItemCount(): Int {
        return reviewList.size?:0
    }
}