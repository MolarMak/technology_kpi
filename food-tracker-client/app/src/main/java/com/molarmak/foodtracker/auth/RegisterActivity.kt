package com.molarmak.foodtracker.auth

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.molarmak.foodtracker.R
import com.molarmak.foodtracker.main.MainActivity
import com.molarmak.foodtracker.more.ProfileUpdateActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.register)

        registerButton.setOnClickListener {
            inputEmail
            inputPassword
            repeatPassword
            startActivity(Intent(this, ProfileUpdateActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
