package com.molarmak.foodtracker.auth.login

import com.google.gson.Gson
import com.molarmak.foodtracker.helper.*
import com.molarmak.foodtracker.main.profile.ProfileData
import com.molarmak.foodtracker.main.profile.ProfileModel

interface LoginPresenterInterface: PresenterType, LoadProfileData {
    fun startToLogin(request: LoginRequest)
    fun endLogin(token: String)
}

class LoginPresenterImpl(private val view: LoginView) : LoginPresenterInterface {

    private val loginModel = LoginModel(this)
    private val profileModel = ProfileModel(this)

    override fun startToLogin(request: LoginRequest) {
        if(!checkData(request)) { return }
        val json = Gson().toJson(request)
        loginModel.login(json)
    }

    override fun endLogin(token: String) {
        Cache.instance.token = token
        startLoadProfileData()
    }

    override fun startLoadProfileData() {
        val token = Cache.instance.token
        if(token != null) {
            profileModel.getProfileData("?token=$token")
        } else onError(ERROR_UNHANDLED)
    }

    override fun endLoadProfileData(data: ProfileData) {
        view.endLogin(data.name != null && data.name.isNotEmpty() && data.age != 0 && data.height != 0 && data.weight != 0)
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

