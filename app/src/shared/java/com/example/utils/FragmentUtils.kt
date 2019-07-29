package com.example.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

infix fun Fragment.findView(@IdRes id: Int): View {
    return requireView().findViewById(id)
}