package com.molarmak.foodtracker.main.profile

import com.google.gson.Gson
import com.molarmak.foodtracker.helper.*

interface ProfilePresenterInterface: PresenterType, LoadProfileData {
    fun startUpdateProfile(request: UpdateProfileForm)
    fun endUpdateProfile()
}

class ProfilePresenterImpl(private val view: ProfileView) : ProfilePresenterInterface {

    private val profileModel = ProfileModel(this)
    private val updateProfileModel = UpdateProfileModel(this)

    override fun startLoadProfileData() {
        val token = Cache.instance.token
        if(token != null) {
            profileModel.getProfileData("?token=$token")
        } else onError(ERROR_UNHANDLED)
    }

    override fun endLoadProfileData(data: ProfileData) {
        view.endLoadProfileData(data)
    }

    override fun startUpdateProfile(request: UpdateProfileForm) {
        if(!checkProfileUpdateRequest(request)) { return }
        val token = Cache.instance.token
        if(token != null) {
            val updateProfileRequest = UpdateProfileRequest(
                token,
                request.name,
                request.age,
                request.height,
                request.weight,
                request.lifeStyle
            )
            val json = Gson().toJson(updateProfileRequest)
            updateProfileModel.update(json)
        } else onError(ERROR_UNHANDLED)
    }

    override fun endUpdateProfile() {
        view.endUpdateProfile()
    }

    override fun onError(errors: ArrayList<String>) {
        view.onError(errors)
    }

    private fun checkProfileUpdateRequest(request: UpdateProfileForm):Boolean {
        if(request.name.isEmpty() || request.height <= 0 || request.weight <= 0) {
            onError(ERROR_FILL_FIELDS)
            return false
        }

        return true
    }
}