package com.molarmak.foodtracker.main.eatFood

import com.molarmak.foodtracker.helper.PresenterType
import com.molarmak.foodtracker.main.overview.FoodResponse

interface EatFoodPresenterInterface: PresenterType {
    fun startLoadAllFood(foodName: String)
    fun endLoadAllFood(foods: ArrayList<FoodResponse>)
}

class EatFoodPresenterImpl(private val view: EatFoodView) : EatFoodPresenterInterface {

    private val allFoodModel = AllFoodModel(this)

    override fun startLoadAllFood(foodName: String) {
        allFoodModel.getAllFood("?foodName=$foodName")
    }

    override fun endLoadAllFood(foods: ArrayList<FoodResponse>) {
        view.endLoadAllFood(foods)
    }

    override fun onError(errors: ArrayList<String>) {
        view.onError(errors)
    }

}