package com.example.new_decuisine

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity


class ListRecipeAdapter(var recipeList: ArrayList<Recipe>): RecyclerView.Adapter<ListRecipeAdapter.ListViewHolder>(){
    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val context = itemView.context
    }

    fun setFilteredList(filteredList: ArrayList<Recipe>){
        recipeList = filteredList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item,parent,false)
        return ListViewHolder(view)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.itemView.apply{
            var photo = findViewById<ImageView>(R.id.recipe_image)
            var namaResep = findViewById<TextView>(R.id.recipe_name)
            var kategoriResep = findViewById<TextView>(R.id.recipe_cat)
            var pembuatResep = findViewById<TextView>(R.id.recipe_creator)
            var button = findViewById<Button>(R.id.button)
            photo.setImageResource(recipeList[position].photo)
            photo.setOnClickListener {
                Toast.makeText(context,recipeList[position].name, Toast.LENGTH_SHORT).show()
            }
            button.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                context.startActivity(intent)
            }
            namaResep.text = recipeList[position].name
            kategoriResep.text = recipeList[position].category
            pembuatResep.text = recipeList[position].creator
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size?:0
    }
}