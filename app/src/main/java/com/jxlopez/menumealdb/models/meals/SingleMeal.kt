package com.jxlopez.menumealdb.models.meals

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SingleMeal(
    val idMeal: String = "",
    val strMeal: String = "",
    val strMealThumb: String = ""
) : Parcelable