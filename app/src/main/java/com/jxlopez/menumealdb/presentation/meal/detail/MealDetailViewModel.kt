package com.jxlopez.menumealdb.presentation.meal.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jxlopez.menumealdb.api.Resource
import com.jxlopez.menumealdb.domain.usescase.mealdetail.GetMealDetailUseCase
import com.jxlopez.menumealdb.domain.usescase.mealsbycategory.GetMealsCategoryUseCase
import com.jxlopez.menumealdb.entities.MealEntity
import com.jxlopez.menumealdb.models.categories.Category
import com.jxlopez.menumealdb.models.meals.SingleMeal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val getMealDetailUseCase: GetMealDetailUseCase
) : ViewModel() {
    var idMeal = ""
    private val _meals = MutableLiveData<Resource<MealEntity>>()
    val meals : LiveData<Resource<MealEntity>>
        get() = _meals

    fun getMealDetail() {
        _meals.postValue(Resource.loading())
        viewModelScope.launch {
            getMealDetailUseCase.getMealDetail(idMeal).collect {
                _meals.postValue(it)
            }
        }
    }
}