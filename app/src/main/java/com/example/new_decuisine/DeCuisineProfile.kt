package com.example.new_decuisine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DeCuisineProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_de_cuisine_profile)
        val btn: Button = findViewById(R.id.btnKembali)

        btn.setOnClickListener {
            this.finish()
        }
    }
}