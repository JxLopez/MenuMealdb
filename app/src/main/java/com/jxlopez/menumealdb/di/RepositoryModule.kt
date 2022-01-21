package com.jxlopez.menumealdb.di

import com.jxlopez.menumealdb.api.ApiHelper
import com.jxlopez.menumealdb.domain.repository.CategoryRepository
import com.jxlopez.menumealdb.domain.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCategoryRepository(apiHelper: ApiHelper): CategoryRepository = CategoryRepository(apiHelper)

    @Provides
    @Singleton
    fun provideMealRepository(apiHelper: ApiHelper): MealRepository = MealRepository(apiHelper)
}