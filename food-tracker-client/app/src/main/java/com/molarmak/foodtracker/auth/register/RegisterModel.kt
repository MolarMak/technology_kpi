package com.molarmak.foodtracker.auth.register

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.molarmak.foodtracker.auth.login.LoginPresenterInterface
import com.molarmak.foodtracker.helper.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

data class RegisterRequest(
    @SerializedName("email") @Expose val email: String,
    @SerializedName("password") @Expose val password: String
)

class RegisterModel(private val view: RegisterPresenterInterface): Callback {

    private val registerRequest = { json: String -> postRequest(json, base_url + registerRoute, this) }
    private val TAG = "RegisterModel"

    override fun onFailure(call: Call?, e: IOException?) {
        apiFailure(TAG, e, view)
    }

    override fun onResponse(call: Call?, response: Response?) {
        val action = { res: JSONObject ->
            if(res.has("token")) {
                view.endRegister(res.getString("token"))
            } else view.onError(ERROR_BAD_RESPONSE)
        }
        apiResponse(TAG, response, view, action)
    }

    fun register(json: String) {
        registerRequest(json)
    }

}