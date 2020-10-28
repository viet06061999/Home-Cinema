package com.sun.homecinema.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sun.homecinema.R
import com.sun.homecinema.base.BottomNavigationListener
import com.sun.homecinema.databinding.ActivityMainBinding
import com.sun.homecinema.utils.hide
import com.sun.homecinema.utils.show

class MainActivity : AppCompatActivity(), BottomNavigationListener {

    private val navController by lazy { findNavController(R.id.navHostFragment) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    override fun showNav() {
        binding.navView.show()
    }

    override fun hideNav() {
        binding.navView.hide()
    }

    private fun setupViews() {
        binding.navView.setupWithNavController(navController)
    }
}
