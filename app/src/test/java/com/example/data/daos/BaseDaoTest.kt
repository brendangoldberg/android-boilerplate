package com.example.data.daos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.App
import com.example.RobolectricTest
import com.example.data.entities.User
import com.example.data.local.AppDatabase
import com.example.utils.CoroutineTestRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BaseDaoTest : RobolectricTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val context = ApplicationProvider.getApplicationContext<App>()

    private val db = AppDatabase.inMemory(context)

    private val userDao = db.userDao()

    @After
    fun setup() {
        db.clearAllTables()
    }

    @Test
    fun `should find by ID`() = coroutineTestRule.runBlockingTest {
        // Given
        val expected = User(
            id = "test-user-1",
            name = "Jane Doe",
            email = "user@test.com",
            address = null,
            phoneNumber = "5555555555"
        )

        userDao.insert(expected)

        // When
        val actual = userDao.findById(expected.id)

        // Then
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `should find all`() = coroutineTestRule.runBlockingTest {
        // Given
        val expected = listOf(
            User(
                id = "test-user-1",
                name = "Jane Doe",
                email = "user@test.com",
                address = null,
                phoneNumber = "5555555555"
            ),
            User(
                id = "test-user-2",
                name = "John Doe",
                email = "user@test.com",
                address = null,
                phoneNumber = "5555555555"
            )
        )

        userDao.insert(expected)

        // When
        val actual = userDao.findAll()

        // Then
        assertThat(actual).isEqualTo(actual)
    }
}