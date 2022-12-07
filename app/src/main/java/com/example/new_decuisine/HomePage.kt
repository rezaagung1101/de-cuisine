package com.example.new_decuisine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomePage : AppCompatActivity() {
    companion object{
        val logList = mutableListOf<AtributeData>()
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        supportActionBar?.hide()
        //redirect ke layout card
        redirectTambah()
        redirectDaftar()
        //Tambah data
        val list = ArrayList<AtributeData>()
//        list.add(AtributeData(resources.getIdentifier("Lontong Balap")))

    }
    private fun redirectTambah() {
        val logBox:View = findViewById(R.id.layoutTambah)
        logBox.setOnClickListener{
//            Toast.makeText(this, "logbox",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TambahResep::class.java)
            startActivity(intent)
        }
    }
    private fun redirectDaftar() {
        val artikelBox:View = findViewById(R.id.layoutListDaftar)
        artikelBox.setOnClickListener{
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }

}