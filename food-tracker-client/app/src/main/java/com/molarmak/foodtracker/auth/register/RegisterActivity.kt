package com.molarmak.foodtracker.auth.register

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.molarmak.foodtracker.R
import com.molarmak.foodtracker.helper.ERROR_FILL_FIELDS
import com.molarmak.foodtracker.helper.ViewType
import com.molarmak.foodtracker.main.MainActivity
import com.molarmak.foodtracker.more.ProfileUpdateActivity
import kotlinx.android.synthetic.main.activity_register.*

interface RegisterView: ViewType {
    fun endRegister(isNotNull: Boolean)
}

class RegisterActivity : AppCompatActivity(), RegisterView {

    private val presenter: RegisterPresenterInterface = RegisterPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.register)

        registerButton.setOnClickListener {
            if(inputPassword == repeatPassword) {
                presenter.startToRegister(
                    RegisterRequest(
                        inputEmail.text.toString(),
                        inputPassword.text.toString()
                    )
                )
            } else onError(ERROR_FILL_FIELDS)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun endRegister(isNotNull: Boolean) {
        if(isNotNull) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, ProfileUpdateActivity::class.java))
        }
    }

    override fun onError(errors: ArrayList<String>) {
        runOnUiThread {
            Toast.makeText(this, errors.joinToString("\n"), Toast.LENGTH_SHORT).show()
        }
    }
}
