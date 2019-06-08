package com.molarmak.foodtracker.main.overview

import com.molarmak.foodtracker.helper.Cache
import com.molarmak.foodtracker.helper.ERROR_UNHANDLED
import com.molarmak.foodtracker.helper.PresenterType

interface CaloriesPresenterInterface : PresenterType {
    fun startLoadCalories()
    fun endLoadCalories(response: CaloriesResponse)
}

class CaloriesPresenterImpl(private val view: CaloriesView) : CaloriesPresenterInterface {

    private val caloriesModel = CaloriesModel(this)

    override fun startLoadCalories() {
        val token = Cache.instance.token
        if(token != null) {
            caloriesModel.getCalories("?token=$token")
        } else onError(ERROR_UNHANDLED)
    }

    override fun endLoadCalories(response: CaloriesResponse) {
        view.endLoadCalories(response)
    }

    override fun onError(errors: ArrayList<String>) {
        view.onError(errors)
    }

}