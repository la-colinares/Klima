package com.lacolinares.klima.data.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_weathers")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("weather_datetime")
    val weatherDateTime: Long = 0L,
    val city: String = "",
    val country: String = "",
    val temperature: Double = 0.00,
    @ColumnInfo("sunrise_time")
    val sunriseTime: Long = 0L,
    @ColumnInfo("sunset_time")
    val sunsetTime: Long = 0L,
    @ColumnInfo("weather_name")
    val weatherName: String = "",
    @ColumnInfo("created_at")
    val createdAt: Long = System.currentTimeMillis()
)