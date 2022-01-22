package com.jxlopez.menumealdb.domain.usescase.mealsrandom

import com.jxlopez.menumealdb.api.Error
import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.api.Status
import com.jxlopez.menumealdb.domain.repository.MealRepository
import com.jxlopez.menumealdb.domain.usescase.mealsbycategory.GetMealsCategoryUseCase
import com.jxlopez.menumealdb.entities.MealEntity
import com.jxlopez.menumealdb.mappers.MealMapper
import com.jxlopez.menumealdb.models.meals.Meal
import com.jxlopez.menumealdb.models.meals.SingleMeal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealRandomInteractor@Inject constructor(
    private val mealsRepository: MealRepository
) : GetMealRandomUseCase {

    override suspend fun randomMeal(): Flow<Resource<MealEntity>> =
        flow {
            val result = mealsRepository.randomMeal().collect { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        val mealEntityList = resource.data?.map(MealMapper.mealEntityMapper)
                        if(mealEntityList?.isNotEmpty() == true)
                            emit(Resource.success(mealEntityList[0]))
                        else {
                            emit(
                                (Resource.error(
                                    "No content", null,
                                    Error(204,"No content")
                                ))
                            )
                        }
                    }
                    Status.ERROR -> {
                        emit(Resource.error(resource.message ?: "", null,
                            Error(resource.error?.errorCode ?: 400,resource.error?.errorMessage ?: "")))
                    }
                }
            }
        }
}