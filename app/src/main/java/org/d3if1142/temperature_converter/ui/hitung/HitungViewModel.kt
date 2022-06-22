package org.d3if1142.temperature_converter.ui.hitung

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if1142.temperature_converter.MainActivity
import org.d3if1142.temperature_converter.R
import org.d3if1142.temperature_converter.db.ConvertorDao
import org.d3if1142.temperature_converter.db.ConvertorEntity
import org.d3if1142.temperature_converter.model.HasilConvert
import org.d3if1142.temperature_converter.model.hasilsuhu
import org.d3if1142.temperature_converter.network.UpdateWorker
import java.util.concurrent.TimeUnit


class HitungViewModel(private val db: ConvertorDao): ViewModel() {



    private val hasilConvert = MutableLiveData<HasilConvert?>()


    fun getHasilConvert(): LiveData<HasilConvert?> = hasilConvert

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

    fun hasilSuhu(nilai: Float, selectedId: Int){

        if (selectedId == R.id.celcius){

            val dataConvert = ConvertorEntity(
                nilai = nilai,
                hasilCelcius = nilai,
                hasilFahrenheit = ((9.0 / 5.0 * nilai) + 32).toFloat(),
                hasilKelvin = nilai + 273.15f,
                selectedId = selectedId
            )

            hasilConvert.value =  dataConvert.hasilsuhu()

            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    db.insert(dataConvert)
                }
            }
        }
        if (selectedId == R.id.fahrenheit){
            val fToCelsi = 5.0 / 9.0 * (nilai - 32)
            val fToKelvin = 5.0 / 9.0 * (nilai - 32) + 273.15
            val dataConvert = ConvertorEntity(
                nilai = nilai,
                hasilCelcius = fToCelsi.toFloat(),
                hasilFahrenheit = nilai,
                hasilKelvin = fToKelvin.toFloat(),
                selectedId = selectedId
            )
            hasilConvert.value =  dataConvert.hasilsuhu()

            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    db.insert(dataConvert)
                }
            }
        }
        if(selectedId == R.id.kelvin){
            val kToCelsi = nilai - 273.15
            val kToFahren = 9.0 / 5.0 * (nilai - 273.15) + 32

            val dataConvert = ConvertorEntity(
                nilai = nilai,
                hasilCelcius = kToCelsi.toFloat(),
                hasilFahrenheit = kToFahren.toFloat(),
                hasilKelvin = nilai,
                selectedId = selectedId
            )
            hasilConvert.value =  dataConvert.hasilsuhu()
            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    db.insert(dataConvert)
                }
            }
        }


    }

}