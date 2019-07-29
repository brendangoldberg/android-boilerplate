package com.example.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.entities.User
import com.example.network.Resource
import com.example.utils.CoroutineTestRule
import com.example.utils.error
import com.example.utils.value
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import retrofit2.Response

@ExperimentalCoroutinesApi
class DefaultUserRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val dbRepository = FakeDBRepository()

    private val serviceRepository = FakeServiceRepository()

    private val userRepository = DefaultUserRepository(dbRepository, serviceRepository)

    private val users = listOf(
        User(id = "test-user-1", name = "Jane Doe", email = "user@test.com", address = null, phoneNumber = "5555555555")
    )

    @Test
    fun `should return local data`() {
        // Given
        val liveData = MutableLiveData<List<User>>().apply { value = users }

        `when`(dbRepository.userDao().findAllLiveData()).thenReturn(liveData)

        // When
        val result = userRepository.getUsers(
            coroutineTestRule.coroutineScope,
            coroutineTestRule.coroutineContext
        ).value()

        // Then
        assertThat(result).isInstanceOf(Resource.Success::class.java)
        assertThat((result as Resource.Success).data).isEqualTo(users)
    }

    @Test
    fun `should return network data`() = coroutineTestRule.coroutineScope.runBlockingTest {
        // Given
        val response = Response.success(users)

        `when`(serviceRepository.userService().getUsers()).thenReturn(response)

        val fromLocal = MutableLiveData<List<User>>().apply { value = emptyList() }
        val fromNetwork = MutableLiveData<List<User>>().apply { value = users }

        `when`(dbRepository.userDao().findAllLiveData()).thenReturn(fromLocal, fromNetwork)

        // When
        val result = userRepository.getUsers(
            coroutineTestRule.coroutineScope,
            coroutineTestRule.coroutineContext
        ).value()

        // Then
        assertThat(result).isInstanceOf(Resource.Success::class.java)
        assertThat((result as Resource.Success).data).isEqualTo(users)
    }

    @Test
    fun `should return error state`() = coroutineTestRule.coroutineScope.runBlockingTest {
        // Given
        val response = error<List<User>>()

        `when`(serviceRepository.userService().getUsers()).thenReturn(response)

        val fromLocal = MutableLiveData<List<User>>().apply { value = emptyList() }

        `when`(dbRepository.userDao().findAllLiveData()).thenReturn(fromLocal)

        // When
        val result = userRepository.getUsers(
            coroutineTestRule.coroutineScope,
            coroutineTestRule.coroutineContext
        ).value()

        // Then
        assertThat(result).isInstanceOf(Resource.Error::class.java)
    }

}