package com.owlsdevelopers.kmpweatherdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.setTitle(R.string.app_name)
        val navController = nav_host_fragment.findNavController()
        toolbar?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

}
