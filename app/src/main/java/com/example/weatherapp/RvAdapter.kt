package com.example.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.RvItemBinding
import com.squareup.picasso.Picasso

class RvAdapter : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private var days = emptyList<Day>()

    class ViewHolder(private val binding : RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(day: Day){
            val url = "https:${day.condition.icon}"
            Picasso.get().load(url).into(binding.rvIcon)
            binding.rvText.text = day.condition.text
            val temp = "Avg: ${day.avgtemp_c}°C"
            binding.rvAvgTemp.text = temp
            val maxWind = "Max wind: ${day.maxwind_kph}kph"
            binding.rvMaxWind.text = maxWind
            val avgHumidity = "Avg humidity: ${day.avghumidity}%"
            binding.rvAvgHumidity.text = avgHumidity
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = days[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = days.size

    fun setData(cards : List<Day>) {
        this.days = cards
        notifyDataSetChanged()
    }
}