package org.d3if1142.temperature_converter.model


import org.d3if1142.temperature_converter.R

import org.d3if1142.temperature_converter.db.ConvertorEntity

fun ConvertorEntity.hasilsuhu(): HasilConvert? {
    if (selectedId == R.id.celcius){
        val cToFahren = 9.0 / 5.0 *  + 32
        val cToKelvin = nilai + 273.15f
        return HasilConvert(nilai,cToFahren.toFloat(),cToKelvin,selectedId)
    }
    if (selectedId == R.id.fahrenheit){
        val fToCelsi = 5.0 / 9.0 * (nilai - 32)
        val fToKelvin = 5.0 / 9.0 * (nilai - 32) + 273.15
        return HasilConvert(fToCelsi.toFloat(),nilai,fToKelvin.toFloat(),selectedId)
    }
    if(selectedId == R.id.kelvin){
        val kToCelsi = nilai - 273.15
        val kToFahren = 9.0 / 5.0 * (nilai - 273.15) + 32
        return HasilConvert(kToCelsi.toFloat(),kToFahren.toFloat(),nilai,selectedId)
    }
    return null
}