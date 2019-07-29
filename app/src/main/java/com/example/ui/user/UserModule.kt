package com.example.ui.user

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.di.FragmentKey
import com.example.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class UserModule {

    @Binds
    @IntoMap
    @FragmentKey(UserFragment::class)
    abstract fun bindUserFragment(fragment: UserFragment): Fragment
}

@Module
@Suppress("unused")
abstract class UserViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(viewModel: UserViewModel): ViewModel

}