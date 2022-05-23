package org.d3if1142.temperature_converter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if1142.temperature_converter.R
import org.d3if1142.temperature_converter.model.HasilConvert

class HitungViewModel: ViewModel() {

    private val hasilConvert = MutableLiveData<HasilConvert?>()

    fun getHasilConvert(): LiveData<HasilConvert?> = hasilConvert



    fun hasilSuhu(nilai: Float, selectedId: Int){

        if (selectedId == R.id.celcius){
            val cToFahren = 9.0 / 5.0 * nilai + 32
            val cToKelvin = nilai + 273.15f
            hasilConvert.value =  HasilConvert(nilai,cToKelvin,cToFahren.toFloat())
        }
        if (selectedId == R.id.fahrenheit){
            val fToCelsi = 5.0 / 9.0 * (nilai - 32)
            val fToKelvin = 5.0 / 9.0 * (nilai - 32) + 273.15
            hasilConvert.value =  HasilConvert(fToCelsi.toFloat(),nilai,fToKelvin.toFloat())

        }
        else{
            val kToCelsi = nilai - 273.15
            val kToFahren = 9.0 / 5.0 * (nilai - 273.15) + 32
            hasilConvert.value =   HasilConvert(kToCelsi.toFloat(),kToFahren.toFloat(),nilai)
        }
    }

}