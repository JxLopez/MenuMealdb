package com.jxlopez.menumealdb.api

import com.jxlopez.menumealdb.models.categories.CategoriesResponse
import com.jxlopez.menumealdb.models.meals.MealsResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getCategories(): Response<CategoriesResponse>
    suspend fun getMealsByCategory(category: String): Response<MealsResponse>
    suspend fun getDetailsMeal(idMeal: String): Response<MealsResponse>
    suspend fun searchMealsByName(nameQuery: String): Response<MealsResponse>
    suspend fun randomMeal(): Response<MealsResponse>
}