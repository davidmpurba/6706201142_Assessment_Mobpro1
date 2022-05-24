package org.d3if1142.temperature_converter.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if1142.temperature_converter.db.ConvertorDao
import org.d3if1142.temperature_converter.db.ConvertorEntity
import org.d3if1142.temperature_converter.model.HasilConvert

class HistoryViewModel(private val db: ConvertorDao):ViewModel() {
    val data = db.getLastHasilConvert()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
    fun hapusSatuData(convertor: ConvertorEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.delete(convertor)
        }
    }
}