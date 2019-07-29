package com.example.data.repositories

class DefaultNameRepository : NameRepository {

    val defaultName = "default name"

    override fun getName(): String {
        return defaultName
    }

}