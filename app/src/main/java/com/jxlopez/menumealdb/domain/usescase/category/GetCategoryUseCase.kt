package com.jxlopez.menumealdb.domain.usescase.category

import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.models.categories.Category
import kotlinx.coroutines.flow.Flow

interface GetCategoryUseCase {
    suspend fun getCategory() : Flow<Resource<List<Category>>>
}