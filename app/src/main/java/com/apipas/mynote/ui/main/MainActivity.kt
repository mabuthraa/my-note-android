package com.apipas.mynote.ui.main

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.apipas.mynote.R
import com.apipas.mynote.databinding.ActivityMainBinding
import com.apipas.mynote.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<ActivityMainBinding, MainVM>(R.layout.activity_main, MainVM::class) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupListeners()
    }

    private fun setupListeners() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)
        // setting title according to fragment
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            toolbar.title = "" // prevent duplication
            toolbar_title.text = navController.currentDestination?.label
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}