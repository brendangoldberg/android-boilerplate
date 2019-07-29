package com.example.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * Creates a [ViewModelProvider.Factory] that generates the
 * specified ViewModels.
 *
 * @param viewModels    variable list of ViewModels to generate within the factory.
 *
 * @return the initialized factory.
 */
inline fun <reified T : ViewModel> createViewModelFactory(
    vararg viewModels: T
): ViewModelProvider.Factory {

    return object : ViewModelProvider.Factory {
        override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
            val creator = viewModels.find { modelClass.isAssignableFrom(it::class.java) }

            val found = creator ?: throw IllegalArgumentException("unknown ViewModel class $modelClass")

            @Suppress("unchecked_cast")
            return found as VM
        }
    }

}


/**
 * Creates a [FragmentFactory] that generates the
 * specified Fragments.
 *
 * @param fragments    variable list of Fragments to generate within the factory.
 *
 * @return the generated factory.
 */
fun createFragmentFactory(vararg fragments: Fragment): FragmentFactory {
    return object : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            // first {} already throws Exception if not found
            return fragments.first {
                it::class.java.name == className
            }
        }
    }
}

