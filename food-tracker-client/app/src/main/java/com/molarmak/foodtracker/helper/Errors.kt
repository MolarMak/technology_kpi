package com.molarmak.foodtracker.helper

import android.app.Activity
import android.widget.Toast

val ERROR_SERVER = arrayListOf("Something went wrong")
val ERROR_BAD_RESPONSE = arrayListOf("Bad response")
val ERROR_UNHANDLED = arrayListOf("Unhandled error")
val ERROR_FILL_FIELDS = arrayListOf("All fields must be filled")

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