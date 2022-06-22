package org.d3if1142.temperature_converter.ui.optionmenu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if1142.temperature_converter.network.ApiStatus
import org.d3if1142.temperature_converter.network.DataApi
import org.d3if1142.temperature_converter.model.MyData
import java.lang.Exception

class AboutMeViewModel : ViewModel() {

    private val aboutMe = MutableLiveData<MyData>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO){
            status.postValue(ApiStatus.LOADING)
            try {
                aboutMe.postValue(DataApi.service.getData())
                Log.d("AboutMeViewModel",aboutMe.value.toString())
                status.postValue(ApiStatus.SUCCESS)
            }catch (e: Exception){
                Log.d("AboutMeViewModel", "Failure: $(e.message")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData():LiveData<MyData> = aboutMe
    fun getStatus(): LiveData<ApiStatus> = status

}