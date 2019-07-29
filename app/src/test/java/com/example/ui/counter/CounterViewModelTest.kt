package com.example.ui.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.utils.CoroutineTestRule
import com.example.utils.value
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CounterViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: CounterViewModel

    @Before
    fun setup() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `should decrement count`() {
        // Given
        val previous = viewModel.count.value()!!

        // When
        viewModel.decreaseCount()

        val after = viewModel.count.value()

        // Then
        assertThat(after).isLessThan(previous)
    }

    @Test
    fun `should increase count`() {
        // Given
        val previous = viewModel.count.value()!!

        // When
        viewModel.increaseCount()

        val after = viewModel.count.value()

        // Then
        assertThat(after).isGreaterThan(previous)
    }
}