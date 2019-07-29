package com.example.ui.main

import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.R
import com.example.RobolectricTest
import com.example.data.repositories.FakeNameRepository
import com.example.data.repositories.NameRepository
import com.example.utils.CoroutineTestRule
import com.example.utils.createViewModelFactory
import com.example.utils.findView
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
class MainFragmentTest : RobolectricTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `should inflate view`() {
        // Given
        val scenario = setupView(FakeNameRepository(""))

        // When
        scenario.onFragment { fragment ->

            // Then
            assertThat(fragment.isVisible).isTrue()
        }
    }

    @Test
    fun `should show empty name`() {
        // Given
        val expected = ""
        val scenario = setupView(FakeNameRepository(expected))

        // When
        scenario.onFragment { fragment ->

            val tvName = (fragment findView R.id.tv_name) as TextView

            // Then
            assertThat(tvName.text).isEqualTo(expected)
        }
    }

    @Test
    fun `should show short name`() {
        // Given
        val expected = "bob"
        val scenario = setupView(FakeNameRepository(expected))

        // When
        scenario.onFragment { fragment ->

            val tvName = (fragment findView R.id.tv_name) as TextView

            // Then
            assertThat(tvName.text).isEqualTo(expected)
        }
    }

    @Test
    fun `should show long name`() {
        // Given
        val expected = "thisisareallyreallylongname"
        val scenario = setupView(FakeNameRepository(expected))

        // When
        scenario.onFragment { fragment ->

            val tvName = (fragment findView R.id.tv_name) as TextView

            // Then
            assertThat(tvName.text).isEqualTo(expected)
        }
    }

    private fun setupView(repository: NameRepository): FragmentScenario<MainFragment> {
        val viewModel = MainViewModel(repository)

        val viewModelFactory = createViewModelFactory(viewModel)

        val fragment = MainFragment(viewModelFactory)

        return launchFragmentInContainer(
            themeResId = R.style.AppTheme
        ) { fragment }
    }

}