package org.d3if1142.temperature_converter.ui.optionmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import org.d3if1142.temperature_converter.network.ApiStatus
import org.d3if1142.temperature_converter.databinding.FragmentAboutmeBinding

class AboutmeFragment : Fragment()  {
    private lateinit var binding: FragmentAboutmeBinding

    private val viewModel: AboutMeViewModel by lazy {
        ViewModelProvider(this)[AboutMeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutmeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {

            binding.nama.text = it.name
            binding.company.text = it.company
            binding.location.text = it.location
            Glide.with(binding.photoprofil.context)
                .load(it.avatar_url)
                .into(binding.photoprofil)

        }
        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }

    }
    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }

            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
                binding.nama.visibility = View.GONE
                binding.company.visibility = View.GONE
                binding.location.visibility = View.GONE

            }
        }
    }
}
