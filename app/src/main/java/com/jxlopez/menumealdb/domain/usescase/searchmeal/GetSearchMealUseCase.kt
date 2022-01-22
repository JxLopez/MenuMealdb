package com.jxlopez.menumealdb.domain.usescase.searchmeal

import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.models.meals.SingleMeal
import kotlinx.coroutines.flow.Flow

interface GetSearchMealUseCase {
    suspend fun searchMealByName(nameQuery: String) : Flow<Resource<List<SingleMeal>>>
}