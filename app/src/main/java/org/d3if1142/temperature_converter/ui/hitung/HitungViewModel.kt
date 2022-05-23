package org.d3if1142.temperature_converter.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if1142.temperature_converter.R
import org.d3if1142.temperature_converter.db.ConvertorDao
import org.d3if1142.temperature_converter.db.ConvertorDb
import org.d3if1142.temperature_converter.db.ConvertorEntity
import org.d3if1142.temperature_converter.model.HasilConvert

class HitungViewModel(private val db: ConvertorDao): ViewModel() {

    private val hasilConvert = MutableLiveData<HasilConvert?>()

    fun getHasilConvert(): LiveData<HasilConvert?> = hasilConvert

    val  data = db.getLastHasilConvert()

    fun hasilSuhu(nilai: Float, selectedId: Int){

        if (selectedId == R.id.celcius){
            val cToFahren = 9.0 / 5.0 * nilai + 32
            val cToKelvin = nilai + 273.15f
            hasilConvert.value =  HasilConvert(nilai,cToFahren.toFloat(),cToKelvin)

            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    val dataHasilConvert = ConvertorEntity(
                        hasilCelcius = nilai,
                        hasilFahrenheit = cToFahren.toFloat(),
                        hasilKelvin = cToKelvin
                    )
                    db.insert(dataHasilConvert)
                }
            }
        }
        if (selectedId == R.id.fahrenheit){
            val fToCelsi = 5.0 / 9.0 * (nilai - 32)
            val fToKelvin = 5.0 / 9.0 * (nilai - 32) + 273.15
            hasilConvert.value =  HasilConvert(fToCelsi.toFloat(),nilai,fToKelvin.toFloat())

            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    val dataHasilConvert = ConvertorEntity(
                        hasilCelcius = fToCelsi.toFloat(),
                        hasilFahrenheit = nilai,
                        hasilKelvin = fToKelvin.toFloat()
                    )
                    db.insert(dataHasilConvert)
                }
            }
        }
        if(selectedId == R.id.kelvin){
            val kToCelsi = nilai - 273.15
            val kToFahren = 9.0 / 5.0 * (nilai - 273.15) + 32
            hasilConvert.value =   HasilConvert(kToCelsi.toFloat(),kToFahren.toFloat(),nilai)

            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    val dataHasilConvert = ConvertorEntity(
                        hasilCelcius = kToCelsi.toFloat(),
                        hasilFahrenheit = kToFahren.toFloat(),
                        hasilKelvin = nilai
                    )
                    db.insert(dataHasilConvert)
                }
            }
        }


    }

}