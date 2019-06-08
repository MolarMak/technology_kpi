package com.molarmak.foodtracker.helper

class Cache private constructor(var token: String? = null) {
    companion object {
        val instance = Cache()
    }
}