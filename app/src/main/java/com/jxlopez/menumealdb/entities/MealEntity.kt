package com.jxlopez.menumealdb.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MealEntity(
    val idMeal: String,
    val strMeal: String,
    val strDrinkAlternate: String?,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String?,
    val strTags: String?,
    val strYoutube: String?,
    val strIngredients: String,
    val strSource: String?,
    val strImageSource: String?,
    val strCreativeCommonsConfirmed: String? = ""
    ) : Parcelable