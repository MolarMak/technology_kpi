package com.molarmak.foodtracker.main.overview

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.molarmak.foodtracker.helper.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

data class CaloriesResponse(
    @SerializedName("calories") @Expose val calories: Int,
    @SerializedName("eatenFood") @Expose val eatenFood: ArrayList<EatenFoodResponse>,
    @SerializedName("ok") @Expose val ok: Boolean
)

data class EatenFoodResponse(
    @SerializedName("id") @Expose val id: String,
    @SerializedName("food") @Expose val food: FoodResponse
)

data class FoodResponse(
    @SerializedName("id") @Expose val id: String,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("protein") @Expose val protein: Int,
    @SerializedName("fats") @Expose val fats: Int,
    @SerializedName("carbohydrates") @Expose val carbohydrates: Int
)

class CaloriesModel(private val view: CaloriesPresenterInterface): Callback {

    private val caloriesRequest = {other:String -> getRequest(baseUrl + caloriesDataRoute, other, this) }
    private val TAG = "CaloriesModel"

    override fun onFailure(call: Call?, e: IOException?) {
        apiFailure(TAG, e, view)
    }

    override fun onResponse(call: Call?, response: Response?) {
        val action = { res: JSONObject ->
            if(res.has("calories") && res.has("eatenFood") && res.has("ok")) {
                val caloriesData = Gson().fromJson(res.toString(), CaloriesResponse::class.java)
                view.endLoadCalories(caloriesData)
            } else view.onError(ERROR_BAD_RESPONSE)
        }
        apiResponse(TAG, response, view, action)
    }

    fun getCalories(other: String) {
        caloriesRequest(other)
    }

}