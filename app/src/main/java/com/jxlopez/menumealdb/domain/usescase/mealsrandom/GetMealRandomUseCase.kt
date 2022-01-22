package com.jxlopez.menumealdb.domain.usescase.mealsrandom

import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.entities.MealEntity
import kotlinx.coroutines.flow.Flow

interface GetMealRandomUseCase {
    suspend fun randomMeal() : Flow<Resource<MealEntity>>
}