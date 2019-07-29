package com.example.ui.counter

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.di.FragmentKey
import com.example.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
abstract class CounterModule {

    @Binds
    @IntoMap
    @FragmentKey(CounterFragment::class)
    abstract fun bindCounterFragment(fragment: CounterFragment): Fragment

}

@Module
@Suppress("unused")
abstract class CounterViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CounterViewModel::class)
    abstract fun bindFeatureViewModel(viewModel: CounterViewModel): ViewModel
}