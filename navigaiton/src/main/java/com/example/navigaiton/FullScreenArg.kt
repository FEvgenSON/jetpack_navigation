package com.example.navigaiton

import android.os.Parcelable
import java.io.Serializable

data class FullScreenArg(
    val prevClass: String,
    val prevInstanceNumber: Int
) : Serializable