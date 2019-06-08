package com.molarmak.foodtracker.auth.register

import com.google.gson.Gson
import com.molarmak.foodtracker.helper.Cache
import com.molarmak.foodtracker.helper.ERROR_FILL_FIELDS
import com.molarmak.foodtracker.helper.LoadProfileData
import com.molarmak.foodtracker.helper.PresenterType

interface RegisterPresenterInterface: PresenterType, LoadProfileData {
    fun startToRegister(request: RegisterRequest)
    fun endRegister(token: String)
}

class RegisterPresenterImpl(private val view: RegisterView) : RegisterPresenterInterface {

    private val registerModel = RegisterModel(this)

    override fun startToRegister(request: RegisterRequest) {
        if(!checkData(request)) { return }
        val json = Gson().toJson(request)
        registerModel.register(json)
    }

    override fun endRegister(token: String) {
        Cache.instance.token = token
    }

    override fun startLoadProfileData() {

    }

    override fun endLoadProfileData() {

    }

    override fun onError(errors: ArrayList<String>) {
        view.onError(errors)
    }

    private fun checkData(request: RegisterRequest):Boolean {
        if(request.email.isEmpty() || request.password.isEmpty()) {
            onError(ERROR_FILL_FIELDS)
            return false
        }

        return true
    }

}