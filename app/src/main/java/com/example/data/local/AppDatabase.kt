package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.daos.UserDao
import com.example.data.entities.User

@Database(
    entities = [
        User::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var _instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (_instance == null) {
                _instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }

            return _instance!!
        }

        fun inMemory(context: Context): AppDatabase {

            _instance = Room.inMemoryDatabaseBuilder(
                context.applicationContext,
                AppDatabase::class.java
            ).allowMainThreadQueries()
                .build()

            return _instance!!
        }
    }

}
