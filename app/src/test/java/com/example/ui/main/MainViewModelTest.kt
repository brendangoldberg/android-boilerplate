package com.example.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.repositories.FakeNameRepository
import com.example.data.repositories.NameRepository
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
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: MainViewModel

    private lateinit var nameRepository: NameRepository

    private val initialName = "some name"

    @Before
    fun setup() {

        nameRepository = FakeNameRepository(initialName)

        viewModel = MainViewModel(nameRepository)

    }

    @Test
    fun `should set name`() {
        // Given
        val name = viewModel.name.value()!!

        // Then
        assertThat(name).isEqualTo(initialName)
    }

    @Test
    fun `should update name`() {
        // Given
        val newName = "new name"

        // When
        viewModel.setName(newName)

        val name = viewModel.name.value()!!

        assertThat(name).isEqualTo(newName)
    }

}