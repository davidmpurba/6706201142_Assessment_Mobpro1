package org.d3if1142.temperature_converter.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "convertor")

data class ConvertorEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var nilai: Float,
    var hasilCelcius: Float,
    var hasilFahrenheit: Float,
    var hasilKelvin: Float,
    var selectedId: Int

    )
