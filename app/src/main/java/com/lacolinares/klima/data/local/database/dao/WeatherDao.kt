package com.lacolinares.klima.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lacolinares.klima.data.local.database.entities.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert
    suspend fun insert(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM tbl_weathers ORDER BY id DESC")
    fun loadWeathers(): Flow<List<WeatherEntity>>

}