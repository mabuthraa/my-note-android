package com.apipas.mynote.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import com.apipas.mynote.R
import com.apipas.mynote.databinding.ActivityMainBinding
import com.apipas.mynote.ui.base.BaseActivity

class MainActivity :
    BaseActivity<ActivityMainBinding, MainVM>(R.layout.activity_main, MainVM::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupListeners()
    }

    private fun setupListeners() {

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        // setting title according to fragment
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            findViewById<Toolbar>(R.id.toolbar).title = navController.currentDestination?.label
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}