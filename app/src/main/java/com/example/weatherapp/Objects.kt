package com.example.weatherapp

data class ForecastResponse(
    val forecastDays: List<Day>
)

data class Day(
    val avgtemp_c: Double,
    val maxwind_kph: Double,
    val totalsnow_cm: Double,
    val avghumidity: Double,
    val condition: Condition
)

data class Condition(
    val text: String,
    val icon: String
)