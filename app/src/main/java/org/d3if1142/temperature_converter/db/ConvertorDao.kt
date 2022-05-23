package org.d3if1142.temperature_converter.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ConvertorDao {

    @Insert
    fun insert(convertor: ConvertorEntity)

    @Query("SELECT * FROM convertor ORDER BY id DESC LIMIT 1")
    fun getLastHasilConvert(): LiveData<ConvertorEntity?>
}