package com.example.data.daos

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import java.lang.reflect.ParameterizedType

abstract class BaseDao<E, T> {

    private val tableName = getTableName()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(items: Collection<E>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(item: E)

    suspend fun findAll(): List<E>? {
        val query = SimpleSQLiteQuery(
            "SELECT * FROM $tableName"
        )

        return doFindAll(query)
    }

    suspend fun findById(id: T): E? {
        val query = SimpleSQLiteQuery(
            "SELECT * FROM $tableName where id = ? limit(1)",
            arrayOf(id as Any)
        )

        return doFindById(query)
    }

    @RawQuery
    protected abstract fun doFindAll(query: SupportSQLiteQuery): List<E>

    @RawQuery
    protected abstract fun doFindById(query: SupportSQLiteQuery): E

    @SuppressWarnings
    private fun getTableName(): String {
        return ((javaClass.superclass.genericSuperclass
                as ParameterizedType).actualTypeArguments[0] as Class<*>).simpleName
    }

}