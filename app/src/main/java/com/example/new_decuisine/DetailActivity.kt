package com.example.new_decuisine

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailActivity : AppCompatActivity() {

    companion object{
        const val NAME = "title"
        const val CREATOR = "creator"
        const val BAHAN = "bahan"
        const val PHOTO = "photo"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val namaResepCol: TextView = findViewById(R.id.tvNamaResep)
        val creatorCol: TextView = findViewById(R.id.tvCreator)
        val bahanCol: TextView = findViewById(R.id.tvBahan)
        val photo: ImageView = findViewById(R.id.ivFotoResep)
        val btn: Button = findViewById(R.id.btnKembali)

        namaResepCol.text = intent.getStringExtra(NAME)
        creatorCol.text = intent.getStringExtra(CREATOR)
        bahanCol.text = intent.getStringExtra(BAHAN)
        photo.setImageResource(intent.getIntExtra(PHOTO,R.drawable.ayam_betutu))

        btn.setOnClickListener {
            this.finish()
        }


    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragment)
            commit()
        }
}