package org.d3if1142.temperature_converter.ui.history


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if1142.temperature_converter.R
import org.d3if1142.temperature_converter.databinding.ItemHistoryBinding
import org.d3if1142.temperature_converter.db.ConvertorEntity
import org.d3if1142.temperature_converter.model.hasilsuhu

class HistoryAdapter: ListAdapter<ConvertorEntity, HistoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ConvertorEntity>() {
                override fun areItemsTheSame(
                    oldData: ConvertorEntity, newData: ConvertorEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: ConvertorEntity, newData: ConvertorEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }



    class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ConvertorEntity) = with(binding) {
            val hasilconvert = item.hasilsuhu()

            if (hasilconvert != null) {
                if (hasilconvert.selectedId == R.id.celcius){
                    hasilTextView.text = root.context.getString(R.string.hasil_convert)
                    dataTextView.text = root.context.getString(R.string.hasil_f,hasilconvert.hasilFahrenheit)
                    dataTextView2.text = root.context.getString(R.string.hasil_k,hasilconvert.hasilKelvin)
                }

                if (hasilconvert.selectedId == R.id.fahrenheit){
                    hasilTextView.text = root.context.getString(R.string.hasil_convert)
                    dataTextView.text = root.context.getString(R.string.hasil_c,hasilconvert.hasilCelcius)
                    dataTextView2.text = root.context.getString(R.string.hasil_k,hasilconvert.hasilKelvin)
                }

                if (hasilconvert.selectedId == R.id.kelvin){
                    hasilTextView.text = root.context.getString(R.string.hasil_convert)
                    dataTextView.text = root.context.getString(R.string.hasil_c,hasilconvert.hasilCelcius)
                    dataTextView2.text = root.context.getString(R.string.hasil_f,hasilconvert.hasilFahrenheit)
                }
            }

        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{

            Log.d("HistoryAdapter",getItem(position).id.toString())

        }
        holder.bind(getItem(position))

    fun getConvertorEntity(position: Int): ConvertorEntity?{
        return getItem(position)
        }
    }


}