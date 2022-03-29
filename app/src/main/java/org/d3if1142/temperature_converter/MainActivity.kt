package org.d3if1142.temperature_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import org.d3if1142.temperature_converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convert.setOnClickListener { convertSuhu() }

    }

    private fun  convertSuhu(){
        Log.d("MainActivity", "Tombol diklik!")
    }
}