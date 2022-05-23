package org.d3if1142.temperature_converter.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.d3if1142.temperature_converter.model.HasilConvert
import org.d3if1142.temperature_converter.model.SelectedId

@Entity(tableName = "convertor")

data class ConvertorEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var nilai: Float,
    var selectedId: SelectedId,
    var hasilConvert: HasilConvert

    )
