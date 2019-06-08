package com.molarmak.foodtracker.helper

import com.molarmak.foodtracker.main.profile.ProfileData

interface PresenterType {
    fun onError(errors: ArrayList<String>)
}

interface ViewType {
    fun onError(errors: ArrayList<String>)
}

interface LoadProfileData: PresenterType {
    fun startLoadProfileData()
    fun endLoadProfileData(data: ProfileData)
}