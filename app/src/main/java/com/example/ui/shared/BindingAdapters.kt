package com.example.ui.shared

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun setIsVisible(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}