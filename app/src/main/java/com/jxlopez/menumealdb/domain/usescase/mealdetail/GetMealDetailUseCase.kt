package com.jxlopez.menumealdb.domain.usescase.mealdetail

import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.entities.MealEntity
import kotlinx.coroutines.flow.Flow

interface GetMealDetailUseCase {
    suspend fun getMealDetail(idMeal: String) : Flow<Resource<MealEntity>>
}