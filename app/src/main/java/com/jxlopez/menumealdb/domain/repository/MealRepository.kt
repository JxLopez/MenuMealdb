package com.jxlopez.menumealdb.domain.repository

import com.jxlopez.menumealdb.api.ApiHelper
import com.jxlopez.menumealdb.api.Error
import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.models.meals.Meal
import com.jxlopez.menumealdb.models.meals.SingleMeal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealRepository@Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getMealsByCategory(category: String) : Flow<Resource<List<SingleMeal>>> =
        flow {
            val result = apiHelper.getMealsByCategory(category)
            if(result.isSuccessful) {
                emit(Resource.success(result.body()?.meals))
            } else {
                emit((Resource.error(result.errorBody().toString(), null,
                    Error(result.code(), result.errorBody().toString())
                )))
            }
        }

    suspend fun getDetailsMeal(idMeal: String) : Flow<Resource<List<Meal>>> =
        flow {
            val result = apiHelper.getDetailsMeal(idMeal)
            if(result.isSuccessful) {
                emit(Resource.success(result.body()?.meals))
            } else {
                emit((Resource.error(result.errorBody().toString(), null,
                    Error(result.code(), result.errorBody().toString())
                )))
            }
        }

    suspend fun searchMealsByName(nameQuery: String) : Flow<Resource<List<Meal>>> =
        flow {
            val result = apiHelper.searchMealsByName(nameQuery)
            if(result.isSuccessful) {
                emit(Resource.success(result.body()?.meals))
            } else {
                emit((Resource.error(result.errorBody().toString(), null,
                    Error(result.code(), result.errorBody().toString())
                )))
            }
        }

    suspend fun randomMeal() : Flow<Resource<List<Meal>>> =
        flow {
            val result = apiHelper.randomMeal()
            if(result.isSuccessful) {
                emit(Resource.success(result.body()?.meals))
            } else {
                emit((Resource.error(result.errorBody().toString(), null,
                    Error(result.code(), result.errorBody().toString())
                )))
            }
        }
}