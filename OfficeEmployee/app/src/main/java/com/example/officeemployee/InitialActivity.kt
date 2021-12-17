package com.example.officeemployee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import dagger.hilt.android.AndroidEntryPoint
import com.example.officeemployee.database.UserPreferences
import com.example.officeemployee.ui.MainActivity
import com.example.officeemployee.ui.auth.AuthActivity
import com.example.officeemployee.util.startNewActivity

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