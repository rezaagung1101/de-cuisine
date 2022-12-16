package com.example.new_decuisine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review (
    val category: String,
    val name: String,
    val creator: String,
    val review: String,
    val rating: Double
): Parcelable
