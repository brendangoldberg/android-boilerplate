package com.example.ui.shared

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import com.example.di.DaggerFragmentFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DaggerNavHostFragment : NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: DaggerFragmentFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
    }

}