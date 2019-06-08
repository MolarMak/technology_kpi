package com.molarmak.foodtracker.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molarmak.foodtracker.R
import kotlinx.android.synthetic.main.fragment_calories.*

class CaloriesFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_calories, null)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        caloriesText
        statusText
        recycler
    }

}