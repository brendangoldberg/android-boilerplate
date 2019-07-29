package com.example.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.FragmentUserBinding
import com.example.network.Resource
import timber.log.Timber
import javax.inject.Inject

class UserFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private val viewModel: UserViewModel by viewModels { viewModelFactory }

    private val adapter: UserAdapter by lazy {
        UserAdapter()
    }

    private lateinit var binding: FragmentUserBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner

            rvUsers.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                adapter = this@UserFragment.adapter
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.users.observe(viewLifecycleOwner, Observer { result ->
            if (result is Resource.Success) {
                adapter.update(result.data)
                binding.isLoading = false
            } else if (result is Resource.Loading) {
                binding.isLoading = true
            }
        })
    }

}