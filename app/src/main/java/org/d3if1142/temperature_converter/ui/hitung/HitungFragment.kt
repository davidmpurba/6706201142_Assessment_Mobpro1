package org.d3if1142.temperature_converter.ui.hitung


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if1142.temperature_converter.R
import org.d3if1142.temperature_converter.databinding.FragmentHitungBinding
import org.d3if1142.temperature_converter.db.ConvertorDao
import org.d3if1142.temperature_converter.db.ConvertorDb
import org.d3if1142.temperature_converter.model.HasilConvert

class HitungFragment : Fragment() {
    private var selectedId = -1

    private  lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = ConvertorDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this,factory)[HitungViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungFragment_to_historyFragment)
                return true
            }
            R.id.menu_about ->{
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
            R.id.menu_aboutme ->{
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutMeFragment)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(layoutInflater,container,false)
        binding = FragmentHitungBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        binding.convert.setOnClickListener { hasilSuhu() }
        binding.reset.setOnClickListener { resetSuhu() }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getHasilConvert().observe(requireActivity()) { showResult(it) }
        binding.bagikan.setOnClickListener { shareData() }
        viewModel.scheduleUpdater(requireActivity().application)


    }

    private fun shareData(){

        val selectedId = binding.radioGroup.checkedRadioButtonId
        val message = getString(R.string.bagikan_template,
            viewModel.getHasilConvert().value!!.hasilCelcius,
            viewModel.getHasilConvert().value!!.hasilFahrenheit,
            viewModel.getHasilConvert().value!!.hasilKelvin)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT,message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
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
             viewModel.hasilSuhu(nilai.toFloat(), selectedId)


    }


    private fun showResult(result: HasilConvert?){
        if (result == null) return

        if (selectedId == R.id.celcius){

            binding.hasil1.text = getString(R.string.hasil_convert3,result.hasilFahrenheit)
            binding.hasil2.text = getString(R.string.hasil_convert2,result.hasilKelvin)
        }
        if (selectedId == R.id.fahrenheit){

            binding.hasil1.text = getString(R.string.hasil_convert1,result.hasilCelcius)
            binding.hasil2.text = getString(R.string.hasil_convert2,result.hasilKelvin)
        }
        if (selectedId == R.id.kelvin){

            binding.hasil1.text = getString(R.string.hasil_convert1,result.hasilCelcius)
            binding.hasil2.text = getString(R.string.hasil_convert3,result.hasilFahrenheit)
        }
        binding.bagikan.visibility = View.VISIBLE

    }

}







