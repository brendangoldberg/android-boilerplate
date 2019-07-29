package com.example

import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AppTest {

    private val context = ApplicationProvider.getApplicationContext<App>()

    @Test
    fun shouldHaveContext() {
        assertThat(context).isNotNull()
    }

}