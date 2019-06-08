package com.molarmak.foodtracker.main.profile

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.molarmak.foodtracker.R
import com.molarmak.foodtracker.helper.ERROR_FILL_FIELDS
import com.molarmak.foodtracker.helper.ViewType
import com.molarmak.foodtracker.main.MainActivity
import kotlinx.android.synthetic.main.view_profile.*
import java.lang.Exception

enum class LifeStyleEnum(val intValue: Int) {
    LOW(1),
    MEDIUM(2),
    HARD(3);

    override fun toString(): String{
        return this.name
    }
}

interface ProfileView: ViewType {
    fun endLoadProfileData(data: ProfileData)
    fun endUpdateProfile()
}

class ProfileFragment: Fragment(), ProfileView {

    private val presenter: ProfilePresenterInterface = ProfilePresenterImpl(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.view_profile, null)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val ageAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, (1..110).toList())
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ageSpinner.adapter = ageAdapter

        val lifestyleAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, listOf(
            LifeStyleEnum.LOW,
            LifeStyleEnum.MEDIUM,
            LifeStyleEnum.HARD
        ))
        lifestyleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        lifestyleSpinner.adapter = lifestyleAdapter

        updateButton.setOnClickListener {
            try {
                presenter.startUpdateProfile(
                    UpdateProfileForm(
                        nameEdit.text.toString(),
                        ageSpinner.selectedItem as Int,
                        heightEdit.text.toString().toInt(),
                        weightEdit.text.toString().toInt(),
                        (lifestyleSpinner.selectedItem as LifeStyleEnum).intValue
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                onError(ERROR_FILL_FIELDS)
            }
        }
    }

    override fun endLoadProfileData(data: ProfileData) {
        activity?.runOnUiThread {
            try {
                heightEdit.setText(data.height.toString())
                weightEdit.setText(data.weight.toString())
                ageSpinner.setSelection(data.age - 1)
                lifestyleSpinner.setSelection(data.lifeStyle - 1)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun endUpdateProfile() {
        activity?.runOnUiThread {
            Toast.makeText(context, getString(R.string.profile_updated), Toast.LENGTH_SHORT).show()
        }
        arguments?.getBoolean(IS_UPDATE_ACTIVITY_BUNDLE)?.let {
            activity?.finish()
            startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onError(errors: ArrayList<String>) {
        activity?.runOnUiThread {
            Toast.makeText(context, errors.joinToString("\n"), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        const val IS_UPDATE_ACTIVITY_BUNDLE = "IS_UPDATE_ACTIVITY_BUNDLE"

        @JvmStatic
        fun newInstance(isUpdateActivity: Boolean) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putBoolean(IS_UPDATE_ACTIVITY_BUNDLE, isUpdateActivity)
            }
        }

    }
}
