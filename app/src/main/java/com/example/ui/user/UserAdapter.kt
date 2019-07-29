package com.example.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.data.entities.User
import com.example.databinding.ViewUserItemBinding
import com.example.ui.shared.BaseAdapter
import com.example.ui.shared.BaseViewHolder

class UserAdapter : BaseAdapter<User, UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(private val binding: ViewUserItemBinding) : BaseViewHolder<User>(binding.root) {

        override fun bind(item: User) {
            binding.tvUserName.text = item.name
        }

    }
}