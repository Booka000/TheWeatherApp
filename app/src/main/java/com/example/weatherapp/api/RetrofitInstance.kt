package com.example.weatherapp.api

import com.example.weatherapp.Day
import com.example.weatherapp.ForecastResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

object RetrofitInstance {

    private val gson = GsonBuilder()
        .registerTypeAdapter(ForecastResponse::class.java, ForecastResponseDeserializer())
        .create()

    val api : ForecastApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ForecastApi::class.java)
    }


    private class ForecastResponseDeserializer : JsonDeserializer<ForecastResponse> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): ForecastResponse {
            val jsonObject = json?.asJsonObject
            val forecastJson = jsonObject?.getAsJsonObject("forecast")
            val forecastDayArray = forecastJson?.getAsJsonArray("forecastday")
            val forecastDayList = forecastDayArray?.mapNotNull { it.asJsonObject?.getAsJsonObject("day") }
            val gson = Gson()
            val days = forecastDayList?.map {gson.fromJson(it, Day::class.java)}
            return ForecastResponse(days ?: emptyList())
        }
    }
}