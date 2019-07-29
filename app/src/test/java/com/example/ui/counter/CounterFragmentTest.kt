package com.example.ui.counter

import android.widget.Button
import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import com.example.R
import com.example.RobolectricTest
import com.example.utils.CoroutineTestRule
import com.example.utils.createViewModelFactory
import com.example.utils.findView
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CounterFragmentTest : RobolectricTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `should inflate view`() {
        // Given
        val scenario = setupView()

        // When
        scenario.onFragment { fragment ->

            // Then
            assertThat(fragment.isVisible).isTrue()
        }
    }

    @Test
    fun `should decrease counter`() {
        // Given
        val scenario = setupView()

        // When
        scenario.onFragment { fragment ->

            val tvCounter = (fragment findView R.id.tv_counter) as TextView

            val previous = tvCounter.text.toString().toInt()

            val btnMinus = (fragment findView R.id.btn_minus) as Button

            btnMinus.performClick()

            val after = tvCounter.text.toString().toInt()

            assertThat(after).isLessThan(previous)
        }
    }

    @Test
    fun `should increase counter`() {
        // Given
        val scenario = setupView()

        // When
        scenario.onFragment { fragment ->

            val tvCounter = (fragment findView R.id.tv_counter) as TextView

            val previous = tvCounter.text.toString().toInt()

            val btnPlus = (fragment findView R.id.btn_plus) as Button

            btnPlus.performClick()

            val after = tvCounter.text.toString().toInt()

            // Then
            assertThat(after).isGreaterThan(previous)
        }
    }

    @Test
    fun `should disable counter when current count is 0`() {
        // Given
        val scenario = setupView()
        val default = CounterViewModel.DEFAULT_COUNT

        // When
        scenario.onFragment { fragment ->

            val btnMinus = (fragment findView R.id.btn_minus) as Button

            for (i in 0 until default) {
                btnMinus.performClick()
            }

            // Then
            assertThat(btnMinus.isEnabled).isFalse()
        }
    }

    private fun setupView(): FragmentScenario<CounterFragment> {

        val viewModel = CounterViewModel()

        val viewModelFactory = createViewModelFactory(viewModel)

        val fragment = CounterFragment(viewModelFactory)

        return launchFragmentInContainer(
            themeResId = R.style.AppTheme
        ) { fragment }
    }

}