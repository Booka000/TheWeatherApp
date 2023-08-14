package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel : ViewModel() {

    val days = MutableLiveData<ForecastResponse>()
    val errorMessage = MutableLiveData<String>()


    fun fetchForecastFromApi(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getForecast()
            } catch (e: IOException) {
                errorMessage.postValue( "No internet connection")
                return@launch
            } catch (e: HttpException) {
                errorMessage.postValue("Unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                days.postValue(response.body())
            } else {
                errorMessage.postValue("Unexpected response")
            }
        }
    }
}