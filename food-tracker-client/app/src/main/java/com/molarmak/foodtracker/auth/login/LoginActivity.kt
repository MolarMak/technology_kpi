package com.molarmak.foodtracker.auth.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.molarmak.foodtracker.R
import com.molarmak.foodtracker.auth.register.RegisterActivity
import com.molarmak.foodtracker.helper.ViewType
import com.molarmak.foodtracker.main.MainActivity
import com.molarmak.foodtracker.more.ProfileUpdateActivity
import kotlinx.android.synthetic.main.activity_login.*

interface LoginView: ViewType {
    fun endLogin(isNull: Boolean)
}

class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter: LoginPresenterInterface = LoginPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.setTitle(R.string.login)

        loginButton.setOnClickListener {
            inputEmail
            inputPassword
            presenter.startToLogin(
                LoginRequest(
                    inputEmail.text.toString(),
                    inputPassword.text.toString()
                )
            )
        }
        registerClick.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun endLogin(isNull: Boolean) {
        if(isNull) {
            startActivity(Intent(this, ProfileUpdateActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onError(errors: ArrayList<String>) {
        runOnUiThread {
            Toast.makeText(this, errors.joinToString("\n"), Toast.LENGTH_SHORT).show()
        }
    }
}
