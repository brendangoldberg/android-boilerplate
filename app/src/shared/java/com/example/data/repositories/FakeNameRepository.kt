package com.example.data.repositories

import com.example.testing.OpenForTesting

@OpenForTesting
class FakeNameRepository(private val name: String) : NameRepository {

    override fun getName(): String {
        return name
    }

}