package com.molarmak.foodtracker.main.eatFood

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.molarmak.foodtracker.helper.*
import com.molarmak.foodtracker.main.overview.FoodResponse
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

data class EatFoodRequest(
        @SerializedName("token") @Expose val token: String,
        @SerializedName("foodId") @Expose val foodId: Int
)

class AllFoodModel(private val view: EatFoodPresenterInterface): Callback {

    private val allFoodRequest = {other:String -> getRequest(baseUrl + allFoodRoute, other, this) }
    private val TAG = "AllFoodModel"

    override fun onFailure(call: Call?, e: IOException?) {
        apiFailure(TAG, e, view)
    }

    override fun onResponse(call: Call?, response: Response?) {
        val action = { res: JSONObject ->
            if(res.has("foods")) {
                val jsonObject = res.getJSONArray("foods")
                val type = object : TypeToken<ArrayList<FoodResponse>>() {}.type
                val allItems: ArrayList<FoodResponse> = Gson().fromJson(jsonObject.toString(), type)
                view.endLoadAllFood(allItems)
            } else view.onError(ERROR_BAD_RESPONSE)
        }
        apiResponse(TAG, response, view, action)
    }

    fun getAllFood(other: String) {
        allFoodRequest(other)
    }

}

class EatFoodModel(private val view: EatFoodPresenterInterface): Callback {

    private val eatFoodRequest = { json: String -> postRequest(json, baseUrl + eatFoodRoute, this) }
    private val TAG = "EatFoodModel"

    override fun onFailure(call: Call?, e: IOException?) {
        apiFailure(TAG, e, view)
    }

    override fun onResponse(call: Call?, response: Response?) {
        val action = { _: JSONObject ->
            view.endEatFood()
        }
        apiResponse(TAG, response, view, action)
    }

    fun eat(json: String) {
        eatFoodRequest(json)
    }

}