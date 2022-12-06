package com.example.new_decuisine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AtributeData (
    val namaResep: String,
    val image: Int,
    val pencipta: String,
    val tahapan: String
):Parcelable
