package com.example.ui.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.databinding.FragmentCounterBinding
import javax.inject.Inject

class CounterFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private val viewModel: CounterViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentCounterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentCounterBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@CounterFragment

            viewModel = this@CounterFragment.viewModel

        }

        handleCTAs()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun handleCTAs() {
        binding.btnMinus.setOnClickListener {
            viewModel.decreaseCount()
        }

        binding.btnPlus.setOnClickListener {
            viewModel.increaseCount()
        }
    }

    private fun observe() {
        viewModel.count.observe(viewLifecycleOwner, Observer { result ->
            binding.btnMinus.isEnabled = result > 0
        })
    }

}