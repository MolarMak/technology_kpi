package com.molarmak.foodtracker.main.profile

import com.molarmak.foodtracker.helper.Cache
import com.molarmak.foodtracker.helper.ERROR_UNHANDLED
import com.molarmak.foodtracker.helper.LoadProfileData
import com.molarmak.foodtracker.helper.PresenterType

interface ProfilePresenterInterface: PresenterType, LoadProfileData

class ProfilePresenterImpl(private val view: LoadProfileData) : ProfilePresenterInterface {

    private val profileModel = ProfileModel(this)

    override fun startLoadProfileData() {
        val token = Cache.instance.token
        if(token != null) {
            profileModel.getProfileData("?token=$token")
        } else onError(ERROR_UNHANDLED)
    }

    override fun endLoadProfileData(data: ProfileData) {
        view.endLoadProfileData(data)
    }

    override fun onError(errors: ArrayList<String>) {
        view.onError(errors)
    }

}