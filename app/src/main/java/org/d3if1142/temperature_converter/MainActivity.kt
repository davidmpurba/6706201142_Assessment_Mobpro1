package org.d3if1142.temperature_converter


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if1142.temperature_converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convert.setOnClickListener { hasilSuhu() }
        binding.reset.setOnClickListener { resetSuhu() }

    }

    private fun resetSuhu(){
        binding.nilaiSuhuInp.text = null
        binding.radioGroup.clearCheck()
        binding.hasil1.text = null
        binding.hasil2.text = null
    }

    private  fun hasilSuhu() {

        val nilai = binding.nilaiSuhuInp.text.toString()
        if (TextUtils.isEmpty(nilai)) {
            Toast.makeText(this, R.string.nilai_invalid, Toast.LENGTH_LONG).show()
            return
        }


        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, R.string.jenis_invalid, Toast.LENGTH_LONG).show()
            return
        }else{
            if (selectedId == R.id.celcius){
                val cToKelvin = nilai.toFloat()+273.15
                val cToFahren = 9.0/5.0 * nilai.toFloat() + 32
                binding.hasil1.text = getString(R.string.hasil_convert2,cToKelvin)
                binding.hasil2.text = getString(R.string.hasil_convert3,cToFahren)
            }
            if (selectedId == R.id.fahrenheit){
                val fToCelsi = 5.0/9.0 * (nilai.toFloat() - 32)
                val fToKelvin = 5.0/9.0 * (nilai.toFloat() - 32) + 273.15
                binding.hasil1.text = getString(R.string.hasil_convert1,fToCelsi)
                binding.hasil2.text = getString(R.string.hasil_convert2,fToKelvin)
            } else {
                val kToCelsi= nilai.toFloat() - 273.15
                val kToFahren = 9.0/5.0 * (nilai.toFloat() - 273.15) + 32
                binding.hasil1.text = getString(R.string.hasil_convert1,kToCelsi)
                binding.hasil2.text = getString(R.string.hasil_convert2,kToFahren)
            }
        }

    }






}




