package com.molarmak.foodtracker.helper

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

val JSON = MediaType.parse("application/json; charset=utf-8")

fun postRequest(json: String, url: String, callback: Callback) {
    val client = OkHttpClient()

    val body = RequestBody.create(JSON, json)
    val requestEntity = Request.Builder().url(url)

    val request = requestEntity.post(body).build()

    client.newCall(request).enqueue(callback)
}

fun getRequest(url: String, other: String, callback: Callback) {
    val client = OkHttpClient()
    val endUrl = "$url$other"

    val requestEntity = Request.Builder().url(endUrl)

    val request = requestEntity.build()
    client.newCall(request).enqueue(callback)
}

fun apiResponse(TAG: String, response: Response?, view: PresenterType, action: (JSONObject) -> Unit) {
    try {
        Log.d(TAG, "response get")
        val responseString = response?.body()?.string()
        Log.d(TAG, responseString)
        if (response?.code() == 200) {
            val resData = JSONObject(responseString)
            if (resData.has("status")) {
                if (resData.getInt("status") == 1) {
                    if (resData.has("data")) {
                        if(!resData.isNull("data")) { //todo change it to response something
                            val res = resData.getJSONObject("data")
                            action(res)
                        } else {
                            Log.i("RESPONSE", "nothing in data")
                            action(JSONObject()) //todo remove it
                        }
                    } else view.onError(ERROR_BAD_RESPONSE)
                } else {
                    if (resData.has("errors")) {
                        val type = object : TypeToken<ArrayList<String>>() {}.type
                        val errors: ArrayList<String> = Gson().fromJson(resData.getString("errors"), type)
                        view.onError(errors)
                    } else view.onError(ERROR_BAD_RESPONSE)
                }
            } else view.onError(ERROR_BAD_RESPONSE)
        } else view.onError(ERROR_SERVER)
    } catch (e: Exception) {
        e.printStackTrace()
        view.onError(ERROR_UNHANDLED)
    }
}

fun apiFailure(TAG: String, e: IOException?, view: PresenterType) {
    Log.d(TAG, "something went wrong")
    Log.d(TAG, e?.toString())
    view.onError(ERROR_SERVER)
}