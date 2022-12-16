package com.example.new_decuisine

import android.content.Intent
import android.media.Rating
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment

class FormReview : AppCompatActivity() {

    companion object{
        const val CAT = "category"
        const val CREATOR = "creator"
        const val REVIEW = "ingridients"
        const val RATING = "rate"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_review)

        val creator = findViewById<EditText>(R.id.etCreator).text.toString()
        val cat = findViewById<EditText>(R.id.etKategori).text.toString()
        val review = findViewById<EditText>(R.id.etReview).text.toString()
        val rate = findViewById<RatingBar>(R.id.ratingBar).rating.toDouble()
        val resep = findViewById<TextView>(R.id.etNamaResep).text.toString()

        val btnTambahReview = findViewById<Button>(R.id.btnTambahReview)
        val btnBack = findViewById<Button>(R.id.btnBackInput)
        val fragment = SecondFragment()

        btnTambahReview.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(SecondFragment.CREATOR,creator)
//            bundle.putString(SecondFragment.REVIEW,review)
//            bundle.putString(SecondFragment.CAT,cat)
//            bundle.putDouble(SecondFragment.RATING,rate)
//            bundle.putString(SecondFragment.RESEP,resep)
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(MainActivity.CREATOR,creator)
            intent.putExtra(MainActivity.CAT,cat)
            intent.putExtra(MainActivity.REVIEW,review)
            intent.putExtra(MainActivity.RESEP,review)
            intent.putExtra(MainActivity.RATING,rate)
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            this.finish()
        }


    }
}