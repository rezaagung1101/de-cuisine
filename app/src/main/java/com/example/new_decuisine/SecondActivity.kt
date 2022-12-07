package com.example.new_decuisine

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {
    lateinit var rvListRecipe: RecyclerView
    lateinit var search: SearchView
    val recipeList = ArrayList<Recipe>()
    val adapter = ListRecipeAdapter(recipeList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search = findViewById(R.id.searchView)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return false
            }
        })
        recipeList.add(Recipe(R.drawable.ayam_betutu,"Spaghetti Bolognese","Kontinental","Devina Hermawan"))
        recipeList.add((Recipe(R.drawable.ayam_betutu,"Cottage Pie","Kontinental","Arnold Poernomo")))
        recipeList.add((Recipe(R.drawable.ayam_betutu,"Rendang","Nusantara","Luvita Ho")))
        recipeList.add((Recipe(R.drawable.ayam_betutu,"Cumi Cabai Hijau","Nusantara","Arnold Poernomo")))
        recipeList.add((Recipe(R.drawable.ayam_betutu,"Pho Mie","Oriental","Devina Hermawan")))
        recipeList.add((Recipe(R.drawable.ayam_betutu,"Tom Yum Goong","Oriental","Devina Hermawan")))
        rvListRecipe = findViewById<RecyclerView>(R.id.rvListRecipe)
        rvListRecipe.adapter = adapter
        rvListRecipe.layoutManager = LinearLayoutManager(this)
    }
    private fun filterList(newText: String?) {
        val filteredList = ArrayList<Recipe>()
        for (item in recipeList){
            if (newText != null) {
                if (item.name.lowercase().contains(newText.lowercase())){
                    filteredList.add(item)
                }
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this,"Resep tidak ditemukan", Toast.LENGTH_SHORT).show()
        }else  {
            adapter.setFilteredList(filteredList)
        }
    }
}