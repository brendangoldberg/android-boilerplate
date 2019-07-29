package com.example.ui.main

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import com.example.R
import com.example.RobolectricTest
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MainActivityTest : RobolectricTest() {

    @Test
    fun `should inflate view`() {
        val scenario = setupView()

        scenario.onActivity { activity ->
            assertThat(activity).isNotNull()
        }
    }

    @Test
    fun `should navigate to counter`() {
        val scenario = setupView()

        val controller = mock(NavController::class.java)

        scenario.onActivity { activity ->
            val view = activity.findViewById<View>(R.id.nav_host_fragment)
            Navigation.setViewNavController(view, controller)

            activity.binding.navMain.btnNavCounter.performClick()
        }

        verify(controller).navigate(R.id.counterFragment)
    }

    @Test
    fun `should navigate to main`() {
        val scenario = setupView()

        val controller = mock(NavController::class.java)

        scenario.onActivity { activity ->
            val view = activity.findViewById<View>(R.id.nav_host_fragment)
            Navigation.setViewNavController(view, controller)

            activity.binding.navMain.btnNavMain.performClick()
        }

        verify(controller).navigate(R.id.mainFragment)
    }

    @Test
    fun `should navigate to users`() {
        val scenario = setupView()

        val controller = mock(NavController::class.java)

        scenario.onActivity { activity ->
            val view = activity.findViewById<View>(R.id.nav_host_fragment)
            Navigation.setViewNavController(view, controller)

            activity.binding.navMain.btnNavUsers.performClick()
        }

        verify(controller).navigate(R.id.userFragment)
    }

    private fun setupView(): ActivityScenario<MainActivity> {
        return ActivityScenario.launch(MainActivity::class.java)
    }

}