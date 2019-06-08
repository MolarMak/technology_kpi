package com.molarmak.foodtracker.main.eatFood

import com.google.gson.Gson
import com.molarmak.foodtracker.helper.Cache
import com.molarmak.foodtracker.helper.PresenterType
import com.molarmak.foodtracker.main.overview.FoodResponse

interface EatFoodPresenterInterface: PresenterType {
    fun startEatFood(foodId: String)
    fun endEatFood()
    fun startLoadAllFood(foodName: String)
    fun endLoadAllFood(foods: ArrayList<FoodResponse>)
}

class EatFoodPresenterImpl(private val view: EatFoodView) : EatFoodPresenterInterface {

    private val allFoodModel = AllFoodModel(this)
    private val eatFoodModel = EatFoodModel(this)

    override fun startEatFood(foodId: String) {
        val token = Cache.instance.token
        if(token != null) {
            val eatFoodRequest = EatFoodRequest(token, foodId.toInt())
            val json = Gson().toJson(eatFoodRequest)
            eatFoodModel.eat(json)
        }
    }

    override fun endEatFood() {
        view.endEatFood()
    }

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