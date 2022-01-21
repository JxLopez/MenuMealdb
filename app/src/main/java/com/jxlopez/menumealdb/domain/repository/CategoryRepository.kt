package com.jxlopez.menumealdb.domain.repository

import com.jxlopez.menumealdb.api.ApiHelper
import com.jxlopez.menumealdb.api.Error
import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.models.categories.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepository@Inject constructor(
    private val apiHelper: ApiHelper
) {

    suspend fun getCategories() : Flow<Resource<List<Category>>> =
    flow {
        val result = apiHelper.getCategories()
        if(result.isSuccessful) {
            emit(Resource.success(result.body()?.categories))
        } else {
            emit((Resource.error(result.errorBody().toString(), null,
                Error(result.code(), result.errorBody().toString())
            )))
        }
    }
}