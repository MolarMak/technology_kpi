package com.molarmak.foodtracker

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_overview -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_eat -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
