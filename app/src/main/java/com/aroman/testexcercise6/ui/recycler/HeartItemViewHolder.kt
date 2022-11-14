package com.aroman.testexcercise6.ui.recycler

import androidx.recyclerview.widget.RecyclerView
import com.aroman.testexcercise6.databinding.ItemHeartBinding
import com.aroman.testexcercise6.domain.entity.HeartData
import java.text.SimpleDateFormat
import java.util.*

class HeartItemViewHolder(private val binding: ItemHeartBinding, private val heartSymbol: String) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HeartData) = with(binding) {
        dateText.text = setDate(item.date)
        timeText.text = setTime(item.date)
        maxBloodPressure.text = item.maxBloodPressure.toString()
        minBloodPressure.text = item.minBloodPressure.toString()
        heartRate.text = StringBuilder()
            .append(heartSymbol)
            .append(item.heartRate.toString())
    }

    private fun setDate(time: Long) = SimpleDateFormat("dd MMMM").format(Date(time))
    private fun setTime(time: Long) = SimpleDateFormat("HH:mm").format(Date(time))
}