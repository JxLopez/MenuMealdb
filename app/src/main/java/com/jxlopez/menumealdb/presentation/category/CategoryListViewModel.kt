package com.jxlopez.menumealdb.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.domain.usescase.category.GetCategoryInteractor
import com.jxlopez.menumealdb.domain.usescase.category.GetCategoryUseCase
import com.jxlopez.menumealdb.domain.usescase.mealsrandom.GetMealRandomUseCase
import com.jxlopez.menumealdb.entities.MealEntity
import com.jxlopez.menumealdb.models.categories.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val mealRandomUseCase: GetMealRandomUseCase
) : ViewModel() {
    private val _categories = MutableLiveData<Resource<List<Category>>>()
    val categories : LiveData<Resource<List<Category>>>
        get() = _categories

    private val _mealRandom = MutableLiveData<Resource<MealEntity>>()
    val mealRandom : LiveData<Resource<MealEntity>>
        get() = _mealRandom

    fun getCategory() {
        viewModelScope.launch {
            getCategoryUseCase.getCategory().collect {
                _categories.postValue(it)
            }
        }
    }

    fun randomMeal() {
        viewModelScope.launch {
            mealRandomUseCase.randomMeal().collect {
                _mealRandom.postValue(it)
            }
        }
    }
}