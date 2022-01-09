package com.example.officemanagerapp.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.example.officemanagerapp.R
import com.example.officemanagerapp.database.UserPreferences
import com.example.officemanagerapp.databinding.ActivityMainBinding
import com.example.officemanagerapp.ui.auth.AuthActivity
import com.example.officemanagerapp.ui.profile.ProfileViewModel
import com.example.officemanagerapp.ui.tempModels.UserFirebase
import com.example.officemanagerapp.util.startNewActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import javax.inject.Inject

const val RESULT_SUCCESS = Activity.RESULT_FIRST_USER

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userPreferences: UserPreferences
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ProfileViewModel>()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginInFirebase()
        //loginSetUp()
        navigationSetUp()
    }

    private fun navigationSetUp() {
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.fragment)

        NavigationUI.setupWithNavController(navView, navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment,
                R.id.passFragment,
                R.id.plannedCategoriesFragment,
                R.id.ordersListFragment),
            drawerLayout
        )

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun performLogout() = lifecycleScope.launch {
        viewModel.logout()
        userPreferences.clear()
        startNewActivity(AuthActivity::class.java)
    }

    /*private fun loginSetUp() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        if (!isLogged) {
            graph.startDestination = R.id.loginFragment
        } else {
            graph.startDestination = R.id.homeFragment
        }
        navHostFragment.navController.graph = graph
    }*/


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun loginInFirebase(){
        val login = "Test2@gmail.com"
        val password = "Password"
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(login, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("AAA", "${it.result?.user?.uid}")
                    return@addOnCompleteListener
                }
            }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(login, password)
            .addOnSuccessListener {
                val uid = FirebaseAuth.getInstance().uid ?: ""
                val user = UserFirebase(login, uid)
                val my_rev = FirebaseDatabase.getInstance().getReference("users/$uid")
                my_rev.setValue(user)
                Log.d("AAA", "success")
                return@addOnSuccessListener
            }
    }
}