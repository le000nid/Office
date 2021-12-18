package com.example.officemanagerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import dagger.hilt.android.AndroidEntryPoint
import com.example.officemanagerapp.database.UserPreferences
import com.example.officemanagerapp.ui.MainActivity
import com.example.officemanagerapp.ui.auth.AuthActivity
import com.example.officemanagerapp.util.startNewActivity

@AndroidEntryPoint
class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
        val userPreferences = UserPreferences(this)

        userPreferences.accessToken.asLiveData().observe(this, {
            val activity = if (it == null) AuthActivity::class.java else MainActivity::class.java
            startNewActivity(activity)
        })
    }
}