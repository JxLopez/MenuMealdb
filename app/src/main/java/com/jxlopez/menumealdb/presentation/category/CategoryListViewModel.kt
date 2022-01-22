package com.jxlopez.menumealdb.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.domain.usescase.category.GetCategoryUseCase
import com.jxlopez.menumealdb.domain.usescase.mealsrandom.GetMealRandomUseCase
import com.jxlopez.menumealdb.domain.usescase.searchmeal.GetSearchMealUseCase
import com.jxlopez.menumealdb.entities.MealEntity
import com.jxlopez.menumealdb.models.categories.Category
import com.jxlopez.menumealdb.models.meals.SingleMeal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val mealRandomUseCase: GetMealRandomUseCase,
    private val searchMealUseCase: GetSearchMealUseCase
) : ViewModel() {
    private val _categories = MutableLiveData<Resource<List<Category>>>()
    val categories : LiveData<Resource<List<Category>>>
        get() = _categories

    private val _mealRandom = MutableLiveData<Resource<MealEntity>>()
    val mealRandom : LiveData<Resource<MealEntity>>
        get() = _mealRandom

    private val _searchMeals = MutableLiveData<Resource<List<SingleMeal>>>()
    val searchMeals : LiveData<Resource<List<SingleMeal>>>
        get() = _searchMeals

    fun getCategory() {
        _categories.postValue(Resource.loading())
        viewModelScope.launch {
            getCategoryUseCase.getCategory().collect {
                _categories.postValue(it)
            }
        }
    }

    fun randomMeal() {
        _mealRandom.postValue(Resource.loading())
        viewModelScope.launch {
            mealRandomUseCase.randomMeal().collect {
                _mealRandom.postValue(it)
            }
        }
    }

    fun searchMealByName(query: String) {
        _searchMeals.postValue(Resource.loading())
        viewModelScope.launch {
            searchMealUseCase.searchMealByName(query).collect {
                _searchMeals.postValue(it)
            }
        }
    }
}