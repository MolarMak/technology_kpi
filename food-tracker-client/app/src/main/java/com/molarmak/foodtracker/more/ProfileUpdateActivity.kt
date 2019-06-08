package com.molarmak.foodtracker.more

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.molarmak.foodtracker.R
import com.molarmak.foodtracker.main.MainActivity
import com.molarmak.foodtracker.main.ProfileFragment

class ProfileUpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_update)

        supportActionBar?.setTitle(R.string.update_profile)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, ProfileFragment.newInstance(true))
        fragmentTransaction.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
        return true
    }
}
