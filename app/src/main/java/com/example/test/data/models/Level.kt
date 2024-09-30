package com.example.test.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Level(
    val imgOffer: String,
    val offers: MutableList<String>,
    val answers: MutableList<String>
): Parcelable