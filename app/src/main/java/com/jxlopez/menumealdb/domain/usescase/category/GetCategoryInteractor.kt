package com.jxlopez.menumealdb.domain.usescase.category

import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.domain.repository.CategoryRepository
import com.jxlopez.menumealdb.models.categories.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryInteractor@Inject constructor(
    private val categoryRepository: CategoryRepository
) : GetCategoryUseCase {

    override suspend fun getCategory(): Flow<Resource<List<Category>>> =
        categoryRepository.getCategories()

}