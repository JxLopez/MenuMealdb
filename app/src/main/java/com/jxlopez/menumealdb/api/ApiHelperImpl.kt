package com.jxlopez.menumealdb.api

import com.jxlopez.menumealdb.models.categories.CategoriesResponse
import com.jxlopez.menumealdb.models.meals.MealsResponse
import com.jxlopez.menumealdb.models.meals.SingleMealResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper {
    override suspend fun getCategories(): Response<CategoriesResponse> =
        apiService.getCategories()

    override suspend fun getMealsByCategory(category: String): Response<SingleMealResponse> =
        apiService.getMealsByCategory(category)

    override suspend fun getDetailsMeal(idMeal: String): Response<MealsResponse> =
        apiService.getDetailsMeal(idMeal)

    override suspend fun searchMealsByName(nameQuery: String): Response<MealsResponse> =
        apiService.searchMealsByName(nameQuery)

    override suspend fun randomMeal(): Response<MealsResponse> =
        apiService.randomMeal()
}