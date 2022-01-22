package com.jxlopez.menumealdb.mappers

import com.jxlopez.menumealdb.entities.MealEntity
import com.jxlopez.menumealdb.models.meals.Meal
import com.jxlopez.menumealdb.models.meals.SingleMeal

object MealMapper {
    val mealEntityMapper: (Meal) -> MealEntity = { meal ->
        MealEntity(
            meal.idMeal,
            meal.strMeal,
            meal.strDrinkAlternate,
            meal.strCategory,
            meal.strArea,
            meal.strInstructions,
            meal.strMealThumb,
            meal.strTags,
            meal.strYoutube,
            ingredientsMealMapper(meal),
            meal.strSource,
            meal.strImageSource,
            meal.strCreativeCommonsConfirmed
        )
    }

    val ingredientsMealMapper: (Meal) -> String = { meal ->
        var ingredients = ""

        if (meal.strIngredient1?.isNotEmpty() == true) {
            ingredients += meal.strMeasure1 + " " + meal.strIngredient1 + "\n"
        }
        if (meal.strIngredient2?.isNotEmpty() == true) {
            ingredients += meal.strMeasure2 + " " + meal.strIngredient2 + "\n"
        }
        if (meal.strIngredient3?.isNotEmpty() == true) {
            ingredients += meal.strMeasure3 + " " + meal.strIngredient3 + "\n"
        }
        if (meal.strIngredient4?.isNotEmpty() == true) {
            ingredients += meal.strMeasure4 + " " + meal.strIngredient4 + "\n"
        }
        if (meal.strIngredient5?.isNotEmpty() == true) {
            ingredients += meal.strMeasure5 + " " + meal.strIngredient5 + "\n"
        }
        if (meal.strIngredient6?.isNotEmpty() == true) {
            ingredients += meal.strMeasure6 + " " + meal.strIngredient6 + "\n"
        }
        if (meal.strIngredient7?.isNotEmpty() == true) {
            ingredients += meal.strMeasure7 + " " + meal.strIngredient7 + "\n"
        }
        if (meal.strIngredient8?.isNotEmpty() == true) {
            ingredients += meal.strMeasure8 + " " + meal.strIngredient8 + "\n"
        }
        if (meal.strIngredient9?.isNotEmpty() == true) {
            ingredients += meal.strMeasure9 + " " + meal.strIngredient9 + "\n"
        }
        if (meal.strIngredient10?.isNotEmpty() == true) {
            ingredients += meal.strMeasure10 + " " + meal.strIngredient10 + "\n"
        }
        if (meal.strIngredient11?.isNotEmpty() == true) {
            ingredients += meal.strMeasure11 + " " + meal.strIngredient11 + "\n"
        }
        if (meal.strIngredient12?.isNotEmpty() == true) {
            ingredients += meal.strMeasure12 + " " + meal.strIngredient12 + "\n"
        }
        if (meal.strIngredient13?.isNotEmpty() == true) {
            ingredients += meal.strMeasure13 + " " + meal.strIngredient13 + "\n"
        }
        if (meal.strIngredient14?.isNotEmpty() == true) {
            ingredients += meal.strMeasure14 + " " + meal.strIngredient14 + "\n"
        }
        if (meal.strIngredient15?.isNotEmpty() == true) {
            ingredients += meal.strMeasure15 + " " + meal.strIngredient15 + "\n"
        }
        if (meal.strIngredient16?.isNotEmpty() == true) {
            ingredients += meal.strMeasure16 + " " + meal.strIngredient16 + "\n"
        }
        if (meal.strIngredient17?.isNotEmpty() == true) {
            ingredients += meal.strMeasure17 + " " + meal.strIngredient17 + "\n"
        }
        if (meal.strIngredient18?.isNotEmpty() == true) {
            ingredients += meal.strMeasure18 + " " + meal.strIngredient18 + "\n"
        }
        if (meal.strIngredient19?.isNotEmpty() == true) {
            ingredients += meal.strMeasure19 + " " + meal.strIngredient19 + "\n"
        }
        if (meal.strIngredient20?.isNotEmpty() == true) {
            ingredients += meal.strMeasure20 + " " + meal.strIngredient20 + "\n"
        }
        ingredients
    }

    val singleMealEntityMapper: (Meal) -> SingleMeal = { meal ->
        SingleMeal(
            meal.idMeal,
            meal.strMeal,
            meal.strMealThumb ?: ""
        )
    }
}