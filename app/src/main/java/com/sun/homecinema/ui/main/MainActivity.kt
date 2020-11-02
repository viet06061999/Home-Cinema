package com.sun.homecinema.ui.main

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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
        binding.navView.animate().translationY(0f)
    }

    override fun hideNav() {
        binding.navView.hide()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        navController.popBackStack()
    }

    private fun setupViews() {
        binding.navView.setupWithNavController(navController)
    }
}
