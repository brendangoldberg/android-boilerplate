package com.example.ui.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.RobolectricTest
import com.example.data.entities.User
import com.example.data.repositories.DefaultUserRepository
import com.example.data.repositories.FakeDBRepository
import com.example.data.repositories.FakeServiceRepository
import com.example.utils.CoroutineTestRule
import com.example.utils.createViewModelFactory
import com.example.utils.findView
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class UserFragmentTest : RobolectricTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val users = listOf(
        User(id = "test-user-1", name = "Jane Doe", email = "user@test.com", address = null, phoneNumber = "5555555555")
    )

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
    fun `should inflate with items`() {
        // Given
        val expected = users
        val scenario = setupView(expected)

        // When
        scenario.onFragment { fragment ->
            val rv = (fragment findView R.id.rv_users) as RecyclerView

            // Then
            assertThat(rv.adapter!!.itemCount).isEqualTo(expected.size)
        }
    }

    private fun setupView(items: List<User> = emptyList()): FragmentScenario<UserFragment> {
        val dbRepository = FakeDBRepository()

        val liveData = MutableLiveData<List<User>>().apply { value = items }

        `when`(dbRepository.userDao().findAllLiveData()).thenReturn(liveData)

        val serviceRepository = FakeServiceRepository()

        val userRepository = DefaultUserRepository(dbRepository, serviceRepository)

        val viewModel = UserViewModel(userRepository)

        val viewModelFactory = createViewModelFactory(viewModel)

        return launchFragmentInContainer { UserFragment(viewModelFactory) }
    }

}