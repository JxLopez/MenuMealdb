package com.jxlopez.menumealdb.api

import com.jxlopez.menumealdb.models.categories.CategoriesResponse
import com.jxlopez.menumealdb.models.meals.MealsResponse
import com.jxlopez.menumealdb.models.meals.SingleMealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): Response<CategoriesResponse>

    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ): Response<SingleMealResponse>

    @GET("lookup.php")
    suspend fun getDetailsMeal(
        @Query("i") idMeal: String
    ): Response<MealsResponse>

    @GET("search.php")
    suspend fun searchMealsByName(
        @Query("f") filterName: String
    ): Response<MealsResponse>

    @GET("random.php")
    suspend fun randomMeal(): Response<MealsResponse>
}