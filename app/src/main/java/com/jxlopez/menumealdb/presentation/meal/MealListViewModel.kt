package com.jxlopez.menumealdb.presentation.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.domain.usescase.mealsbycategory.GetMealsCategoryUseCase
import com.jxlopez.menumealdb.models.categories.Category
import com.jxlopez.menumealdb.models.meals.SingleMeal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(
    private val getMealsCategoryUseCase: GetMealsCategoryUseCase
) : ViewModel() {

    var category = Category()
    private val _meals = MutableLiveData<Resource<List<SingleMeal>>>()
    val meals : LiveData<Resource<List<SingleMeal>>>
        get() = _meals

    fun getMealsByCategory() {
        viewModelScope.launch {
            getMealsCategoryUseCase.getMealsByCategory(category.strCategory).collect {
                _meals.postValue(it)
            }
        }
    }

}