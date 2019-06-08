package com.molarmak.foodtracker.auth.login

import com.google.gson.Gson
import com.molarmak.foodtracker.helper.Cache
import com.molarmak.foodtracker.helper.ERROR_FILL_FIELDS
import com.molarmak.foodtracker.helper.LoadProfileData
import com.molarmak.foodtracker.helper.PresenterType

interface LoginPresenterInterface: PresenterType, LoadProfileData {
    fun startToLogin(request: LoginRequest)
    fun endLogin(token: String)
}

class LoginPresenterImpl(private val view: LoginView) : LoginPresenterInterface {

    private val loginModel = LoginModel(this)

    override fun startToLogin(request: LoginRequest) {
        if(!checkData(request)) { return }
        val json = Gson().toJson(request)
        loginModel.login(json)
    }

    override fun endLogin(token: String) {
        Cache.instance.token = token
    }

    override fun startLoadProfileData() {

    }

    override fun endLoadProfileData() {

    }

    override fun onError(errors: ArrayList<String>) {
        view.onError(errors)
    }

    private fun checkData(request: LoginRequest):Boolean {
        if(request.email.isEmpty() || request.password.isEmpty()) {
            onError(ERROR_FILL_FIELDS)
            return false
        }

        return true
    }

}

