package com.aroman.testexcercise6.ui.recycler

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aroman.testexcercise6.R
import com.aroman.testexcercise6.databinding.ItemHeartBinding
import com.aroman.testexcercise6.domain.entity.HeartData
import java.text.SimpleDateFormat
import java.util.*

class HeartItemViewHolder(
    private val binding: ItemHeartBinding,
    private val data: List<HeartData>,
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HeartData) = with(binding) {
        dateText.text = setDate(item.date)
        timeText.text = setTime(item.date)
        maxBloodPressure.text = item.maxBloodPressure.toString()
        minBloodPressure.text = item.minBloodPressure.toString()
        heartRate.text = StringBuilder()
            .append(itemView.context.getString(R.string.heart_symbol))
            .append(item.heartRate.toString())
        setDateVisibility()
        setBackground(item.maxBloodPressure.toString().takeLast(2).toInt())
    }

    private fun setBackground(maxBloodPressure: Int) {
        var tempBloodPressure = maxBloodPressure * 5
        if (tempBloodPressure > 255) tempBloodPressure = 255
        val hex = Integer.toHexString(tempBloodPressure)
        binding.heartDataLayout.background = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#${hex}DD8D"),
                Color.parseColor("#FFFFFF"),
            )
        )
    }

    private fun setDateVisibility() {
        if (adapterPosition != 0) {
            if (setDate(data[adapterPosition].date) == setDate(data[adapterPosition - 1].date))
                binding.dateText.visibility = View.GONE
            else {
                binding.dateText.visibility = View.VISIBLE
            }
        } else {
            binding.dateText.visibility = View.VISIBLE
        }
    }

    private fun setDate(time: Long) = SimpleDateFormat("dd MMMM").format(Date(time))
    private fun setTime(time: Long) = SimpleDateFormat("HH:mm").format(Date(time))
}