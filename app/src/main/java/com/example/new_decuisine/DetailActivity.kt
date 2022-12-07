package com.example.new_decuisine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object{
        const val TITLE = "title"
        const val CREATOR = "creator"
        const val BAHAN = "bahan"
        const val PHOTO = "photo"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        val namaResepCol: TextView = findViewById(R.id.tvNamaResep)
//        val creatorCol: TextView = findViewById(R.id.tvCreator)
//        val bahanCol: TextView = findViewById(R.id.tvBahan)
//
//        namaResepCol.text = intent.getStringExtra(TITLE)
//        creatorCol.text = intent.getStringExtra(CREATOR)
//        bahanCol.text = intent.getStringExtra(BAHAN)


    }
}