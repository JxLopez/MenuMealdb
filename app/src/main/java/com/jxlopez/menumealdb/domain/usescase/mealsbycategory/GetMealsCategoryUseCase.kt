package com.jxlopez.menumealdb.domain.usescase.mealsbycategory

import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.models.meals.SingleMeal
import kotlinx.coroutines.flow.Flow

interface GetMealsCategoryUseCase {
    suspend fun getMealsByCategory(category: String) : Flow<Resource<List<SingleMeal>>>
}