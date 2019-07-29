package com.example.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.utils.CoroutineTestRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NameRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var repository: DefaultNameRepository

    @Test
    fun `should return default name`() {
        repository = DefaultNameRepository()

        assertThat(repository.getName()).isEqualTo(repository.defaultName)
    }

}