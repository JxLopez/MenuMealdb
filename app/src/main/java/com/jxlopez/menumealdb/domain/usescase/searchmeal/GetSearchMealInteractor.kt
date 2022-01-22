package com.jxlopez.menumealdb.domain.usescase.searchmeal

import com.jxlopez.menumealdb.api.Error
import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.api.Status
import com.jxlopez.menumealdb.domain.repository.MealRepository
import com.jxlopez.menumealdb.mappers.MealMapper
import com.jxlopez.menumealdb.models.meals.SingleMeal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchMealInteractor @Inject constructor(
    private val mealsRepository: MealRepository
) : GetSearchMealUseCase {
    override suspend fun searchMealByName(nameQuery: String): Flow<Resource<List<SingleMeal>>>  =
        flow {
            mealsRepository.searchMealsByName(nameQuery).collect { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        var mealEntityList = resource.data?.map(MealMapper.singleMealEntityMapper)
                        if(mealEntityList == null)
                            mealEntityList = arrayListOf()

                        emit(Resource.success(mealEntityList))
                    }
                    Status.ERROR -> {
                        emit(Resource.error(resource.message ?: "", null,
                            Error(resource.error?.errorCode ?: 400,resource.error?.errorMessage ?: "")
                        ))
                    }
                }
            }
        }
}