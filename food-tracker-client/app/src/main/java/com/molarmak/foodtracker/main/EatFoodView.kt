package com.molarmak.foodtracker.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molarmak.foodtracker.R
import com.molarmak.foodtracker.more.AddFoodActivity
import kotlinx.android.synthetic.main.fragment_eat.*

class EatFoodFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_eat, null)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchEdit
        recycler
        eatButton.setOnClickListener {

        }
        addButton.setOnClickListener {
            activity?.finish()
            startActivity(Intent(context, AddFoodActivity::class.java))
        }
    }
}