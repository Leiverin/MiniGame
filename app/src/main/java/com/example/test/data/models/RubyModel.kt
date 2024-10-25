package com.example.test.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RubyModel (
    val id: Int,
    val targetLevel: Int,
    val point: Int,
    val earned: Boolean
): Parcelable