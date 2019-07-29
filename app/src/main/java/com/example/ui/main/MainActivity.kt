package com.example.ui.main

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.R
import com.example.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        handleNavigation()
    }

    private fun handleNavigation() {

        binding.navMain.btnNavMain.setOnClickListener { navigate(R.id.mainFragment) }

        binding.navMain.btnNavCounter.setOnClickListener { navigate(R.id.counterFragment) }

        binding.navMain.btnNavUsers.setOnClickListener { navigate(R.id.userFragment) }

    }

    private fun navigate(@IdRes id: Int) {
        findNavController(R.id.nav_host_fragment).navigate(id)
    }
}