package com.example.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.data.entities.User

@Dao
abstract class UserDao : BaseDao<User, String>() {

    @Query("SELECT * FROM User ")
    abstract fun findAllLiveData(): LiveData<List<User>>
}