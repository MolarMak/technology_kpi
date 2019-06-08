package com.molarmak.foodtracker.helper

interface PresenterType {
    fun onError(errors: ArrayList<String>)
}

interface ViewType {
    fun onError(errors: ArrayList<String>)
}

interface MainActivityUpdatedFragments {
    fun update(eventId: Int)
}

interface LoadProfileData {
    fun startLoadProfileData()
    fun endLoadProfileData()
}