package com.example.new_decuisine
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe (
    val photo: Int,
    val name: String,
    val category: String,
    val creator: String,
    val ingridients: String
): Parcelable