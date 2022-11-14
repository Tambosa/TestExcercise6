package com.aroman.testexcercise6.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aroman.testexcercise6.databinding.ItemHeartBinding
import com.aroman.testexcercise6.domain.entity.HeartData

class MainActivityAdapter() :
    RecyclerView.Adapter<HeartItemViewHolder>() {
    private var data = listOf<HeartData>()

    fun setData(heartDataList: List<HeartData>) {
        data = heartDataList
        notifyDataSetChanged()
    }

    fun getData() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HeartItemViewHolder(
        ItemHeartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), data
    )

    override fun onBindViewHolder(holder: HeartItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}