package org.d3if1142.temperature_converter.ui


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if1142.temperature_converter.R
import org.d3if1142.temperature_converter.databinding.FragmentHitungBinding
import org.d3if1142.temperature_converter.model.HasilConvert

class HitungFragment : Fragment() {
    private var selectedId = -1

    private  lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        ViewModelProvider(requireActivity())[HitungViewModel::class.java]
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
//    (savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.convert.setOnClickListener { hasilSuhu() }
//        binding.reset.setOnClickListener { resetSuhu() }
//
//        viewModel.getHasilConvert().observe(this, {showResult(it)})
//
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.convert.setOnClickListener { hasilSuhu() }
        binding.reset.setOnClickListener { resetSuhu() }
        viewModel.getHasilConvert().observe(requireActivity(), {showResult(it)})
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
                Toast.makeText(context, R.string.nilai_invalid, Toast.LENGTH_LONG).show()
                return
            }


            selectedId = binding.radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(context, R.string.jenis_invalid, Toast.LENGTH_LONG).show()
                return

            }
             viewModel.hasilSuhu(nilai.toFloat(),selectedId)


    }


    private fun showResult(result: HasilConvert?){
        if (result == null) return

        if (selectedId == R.id.celcius){

            binding.hasil1.text = getString(R.string.hasil_convert2,result.hasilFahrenheit)
            binding.hasil2.text = getString(R.string.hasil_convert3,result.hasilKelvin)
        }
        if (selectedId == R.id.fahrenheit){

            binding.hasil1.text = getString(R.string.hasil_convert1,result.hasilCelcius)
            binding.hasil2.text = getString(R.string.hasil_convert3,result.hasilKelvin)
        }
        if (selectedId == R.id.kelvin){

            binding.hasil1.text = getString(R.string.hasil_convert1,result.hasilCelcius)
            binding.hasil2.text = getString(R.string.hasil_convert2,result.hasilFahrenheit)
        }

    }

}






