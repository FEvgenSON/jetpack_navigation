package com.example.jetpacknaviagtion

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FullScreenArg(
    val prevClass: String,
    val prevInstanceNumber: Int
) : Parcelable