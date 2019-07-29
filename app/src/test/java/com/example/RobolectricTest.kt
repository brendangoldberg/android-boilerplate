package com.example

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testing.OpenForTesting
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Helper parent class to setup Robolectric tests.
 */
@RunWith(AndroidJUnit4::class)
@Config(
    application = App::class,
    sdk = [BuildConfig.MAX_SDK_VERSION]
)
abstract class RobolectricTest