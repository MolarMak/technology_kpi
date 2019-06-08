package com.molarmak.foodtracker.helper

import android.app.Activity
import android.widget.Toast

val ERROR_SERVER = arrayListOf("Something went wrong")
val ERROR_BAD_RESPONSE = arrayListOf("Bad response")
val ERROR_UNHANDLED = arrayListOf("Unhandled error")
val ERROR_FILL_FIELDS = arrayListOf("All fields must be filled")
val ERROR_AGREE = arrayListOf("You must agree to the agreements and rules")
val ERROR_WIFI_CONNECT = arrayListOf("Can't connect to wifi")
val ERROR_ESP_WIFI_CONNECT = arrayListOf("Can't connect to gateway")
val ERROR_INCORRENT_WIFI_PASS = arrayListOf("Incorrect wifi password")
val ERROR_WIFI_INTERNET = arrayListOf("This network can't connect to server")

val errorHandleToast = { activity: Activity?, isVisible: Boolean, errors: ArrayList<String> ->
    errorHandle(activity, isVisible) {
        errors.forEach {
            Toast.makeText(activity!!, it, Toast.LENGTH_SHORT).show()
        }
    }
}

fun errorHandle(activity: Activity?, isVisible: Boolean, functionality: () -> Unit) {
    if(activity != null && isVisible) {
        activity.runOnUiThread {
            functionality()
        }
    }
}