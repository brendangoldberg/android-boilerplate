package com.example.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.models.Address
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "User")
@JsonClass(generateAdapter = true)
data class User(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val address: Address?,
    @Json(name = "phone") @ColumnInfo(name = "phone_number") val phoneNumber: String
)