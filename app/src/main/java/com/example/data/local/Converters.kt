package com.example.data.local

import androidx.room.TypeConverter
import com.example.data.models.Address
import com.example.data.models.AddressJsonAdapter
import com.squareup.moshi.Moshi

class Converters {

    private val moshi = Moshi.Builder().build()

    private val addressAdapter = AddressJsonAdapter(moshi)

    @TypeConverter
    fun toAddress(value: String): Address? = addressAdapter.fromJson(value)

    @TypeConverter
    fun fromAddress(value: Address): String = addressAdapter.toJson(value)

}