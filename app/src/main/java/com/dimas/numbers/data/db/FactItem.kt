package com.dimas.numbers.data.db

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FactItem(
    @SerializedName("number")
    val number: Long,
    @SerializedName("text")
    val aboutNumber: String
) : Parcelable


