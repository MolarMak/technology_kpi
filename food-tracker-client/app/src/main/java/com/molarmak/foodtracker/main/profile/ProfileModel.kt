package com.molarmak.foodtracker.main.profile

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.molarmak.foodtracker.helper.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

data class ProfileData(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("age") @Expose val age: Int,
    @SerializedName("height") @Expose val height: Int,
    @SerializedName("weight") @Expose val weight: Int,
    @SerializedName("lifeStyle") @Expose val lifeStyle: Int
)

class ProfileModel(private val view: LoadProfileData): Callback {

    private val profileRequest = {other:String -> getRequest(baseUrl + profileData, other, this)}
    private val TAG = "ProfileModel"

    override fun onFailure(call: Call?, e: IOException?) {
        apiFailure(TAG, e, view)
    }

    override fun onResponse(call: Call?, response: Response?) {
        val action = { res: JSONObject ->
            if(res.has("name")) {
                val profileData = Gson().fromJson(res.toString(), ProfileData::class.java)
                view.endLoadProfileData(profileData)
            } else view.onError(ERROR_BAD_RESPONSE)
        }
        apiResponse(TAG, response, view, action)
    }

    fun getProfileData(other: String) {
        profileRequest(other)
    }

}