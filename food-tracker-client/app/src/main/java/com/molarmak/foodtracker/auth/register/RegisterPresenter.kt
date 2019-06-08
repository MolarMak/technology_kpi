package com.molarmak.foodtracker.auth.register

import com.google.gson.Gson
import com.molarmak.foodtracker.helper.*
import com.molarmak.foodtracker.main.profile.ProfileData
import com.molarmak.foodtracker.main.profile.ProfileModel

interface RegisterPresenterInterface: PresenterType, LoadProfileData {
    fun startToRegister(request: RegisterRequest)
    fun endRegister(token: String)
}

class RegisterPresenterImpl(private val view: RegisterView) : RegisterPresenterInterface {

    private val registerModel = RegisterModel(this)
    private val profileModel = ProfileModel(this)

    override fun startToRegister(request: RegisterRequest) {
        if(!checkData(request)) { return }
        val json = Gson().toJson(request)
        registerModel.register(json)
    }

    override fun endRegister(token: String) {
        Cache.instance.token = token
    }

    override fun startLoadProfileData() {
        val token = Cache.instance.token
        if(token != null) {
            profileModel.getProfileData("?token=$token")
        } else onError(ERROR_UNHANDLED)
    }

    override fun endLoadProfileData(data: ProfileData) {
        view.endRegister(data.name.isNotEmpty() && data.age != 0 && data.height != 0 && data.weight != 0)
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