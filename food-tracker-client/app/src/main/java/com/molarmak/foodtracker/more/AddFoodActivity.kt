package com.molarmak.foodtracker.more

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.molarmak.foodtracker.R
import com.molarmak.foodtracker.main.MainActivity

class AddFoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.add_food)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        startActivity(Intent(applicationContext, MainActivity::class.java))
        return true
    }
}
