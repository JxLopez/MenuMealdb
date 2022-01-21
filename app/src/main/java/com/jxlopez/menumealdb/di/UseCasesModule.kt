package com.jxlopez.menumealdb.di

import com.jxlopez.menumealdb.api.ApiHelper
import com.jxlopez.menumealdb.domain.repository.CategoryRepository
import com.jxlopez.menumealdb.domain.repository.MealRepository
import com.jxlopez.menumealdb.domain.usescase.category.GetCategoryInteractor
import com.jxlopez.menumealdb.domain.usescase.category.GetCategoryUseCase
import com.jxlopez.menumealdb.domain.usescase.mealsbycategory.GetMealsCategoryInteractor
import com.jxlopez.menumealdb.domain.usescase.mealsbycategory.GetMealsCategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun providesGetCategoryUseCases(categoryRepository: CategoryRepository): GetCategoryUseCase = GetCategoryInteractor(categoryRepository)

    @Provides
    fun providesGetMealsCategoryUseCases(mealRepository: MealRepository): GetMealsCategoryUseCase = GetMealsCategoryInteractor(mealRepository)
}