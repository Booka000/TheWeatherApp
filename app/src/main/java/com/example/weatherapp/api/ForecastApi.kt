package com.example.weatherapp.api

import com.example.weatherapp.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET

interface ForecastApi {
    @GET("/v1/forecast.json?q=Nizhny%20Novgorod&days=5&key=f1e4bbb90a0c4348a1082633231408")
    suspend fun getForecast(): Response<ForecastResponse>
}