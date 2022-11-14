package com.aroman.testexcercise6.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aroman.testexcercise6.data.FakeRepoImpl
import com.aroman.testexcercise6.databinding.ActivityMainBinding
import com.aroman.testexcercise6.ui.recycler.MainActivityAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainActivityViewModel(FakeRepoImpl())
    private val heartAdapter = MainActivityAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipeRefreshContainer.isRefreshing = true
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.heartRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.heartRecyclerView.adapter = heartAdapter
        binding.swipeRefreshContainer.setOnRefreshListener {
            binding.swipeRefreshContainer.isRefreshing = true
            viewModel.loadHeartList()
        }
    }

    private fun initViewModel() {
        viewModel.heartDataList.observe(this) {
            heartAdapter.setData(it)
            binding.swipeRefreshContainer.isRefreshing = false
        }
        viewModel.loadHeartList()
    }
}