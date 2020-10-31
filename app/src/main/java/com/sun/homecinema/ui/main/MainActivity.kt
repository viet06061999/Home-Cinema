package com.sun.homecinema.ui.main

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
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
    private lateinit var mDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mDetector = GestureDetectorCompat(this, MyGestureListener())
        setupViews()
    }

    override fun showNav() {
        binding.navView.show()
        binding.navView.animate().translationY(0f)
    }

    override fun hideNav() {
        binding.navView.hide()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        println(event.action)
        println("touch")
        return super.onTouchEvent(event)
    }

    private fun setupViews() {
        binding.navView.setupWithNavController(navController)
    }
    private class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(event: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            event1: MotionEvent,
            event2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            return true
        }
    }
}
