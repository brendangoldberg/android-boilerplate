package com.example.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class CoroutineTestRule : TestRule {

    private val _coroutineContext = TestCoroutineDispatcher()
    private val _coroutineScope = TestCoroutineScope(_coroutineContext)

    val coroutineScope get() = _coroutineScope
    val coroutineContext get() = _coroutineContext

    override fun apply(base: Statement, description: Description): Statement {

        return object : Statement() {

            override fun evaluate() {
                try {
                    Dispatchers.setMain(_coroutineContext)
                    base.evaluate()
                } finally {
                    Dispatchers.resetMain()
                    _coroutineScope.cleanupTestCoroutines()
                }
            }

        }
    }

}