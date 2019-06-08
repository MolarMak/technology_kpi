package com.molarmak.foodtracker.auth.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.molarmak.foodtracker.helper.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

data class LoginRequest(
    @SerializedName("email") @Expose val email: String,
    @SerializedName("password") @Expose val password: String
)

class LoginModel(private val view: LoginPresenterInterface): Callback {

    private val loginRequest = { json: String -> postRequest(json, baseUrl + loginRoute, this) }
    private val TAG = "LoginModel"

    override fun onFailure(call: Call?, e: IOException?) {
        apiFailure(TAG, e, view)
    }

    override fun onResponse(call: Call?, response: Response?) {
        val action = { res: JSONObject ->
            if(res.has("token")) {
                view.endLogin(res.getString("token"))
            } else view.onError(ERROR_BAD_RESPONSE)
        }
        apiResponse(TAG, response, view, action)
    }

    fun login(json: String) {
        loginRequest(json)
    }

}