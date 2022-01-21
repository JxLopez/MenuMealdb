package com.jxlopez.menumealdb.domain.usescase.mealsbycategory

import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.domain.repository.MealRepository
import com.jxlopez.menumealdb.models.meals.SingleMeal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealsCategoryInteractor@Inject constructor(
    private val mealsRepository: MealRepository
) : GetMealsCategoryUseCase {

    override suspend fun getMealsByCategory(category: String): Flow<Resource<List<SingleMeal>>> =
        mealsRepository.getMealsByCategory(category)

}