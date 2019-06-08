package com.molarmak.foodtracker.auth

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.molarmak.foodtracker.R
import com.molarmak.foodtracker.main.MainActivity
import com.molarmak.foodtracker.more.ProfileUpdateActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.setTitle(R.string.login)

        loginButton.setOnClickListener {
            inputEmail
            inputPassword
            startActivity(Intent(this, ProfileUpdateActivity::class.java))
        }
        registerClick.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
