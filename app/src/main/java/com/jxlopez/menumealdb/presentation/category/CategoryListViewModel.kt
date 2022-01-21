package com.jxlopez.menumealdb.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.domain.usescase.category.GetCategoryInteractor
import com.jxlopez.menumealdb.domain.usescase.category.GetCategoryUseCase
import com.jxlopez.menumealdb.models.categories.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoryUse: GetCategoryUseCase
) : ViewModel() {
    private val _res = MutableLiveData<Resource<List<Category>>>()
    val res : LiveData<Resource<List<Category>>>
        get() = _res

    fun getCategory() {
        viewModelScope.launch {
            getCategoryUse.getCategory().collect {
                _res.postValue(it)
            }
        }
    }
}