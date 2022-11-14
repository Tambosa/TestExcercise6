package com.aroman.testexcercise6.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aroman.testexcercise6.R
import com.aroman.testexcercise6.data.FakeRepoImpl
import com.aroman.testexcercise6.databinding.ActivityMainBinding
import com.aroman.testexcercise6.databinding.FabDialogBinding
import com.aroman.testexcercise6.domain.entity.HeartData
import com.aroman.testexcercise6.ui.recycler.MainActivityAdapter
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainActivityViewModel(FakeRepoImpl())
    private lateinit var heartAdapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        heartAdapter = MainActivityAdapter()

        binding.swipeRefreshContainer.isRefreshing = true
        initViewModel()
        initFab()
        initRecyclerView()

        viewModel.loadHeartList()
    }

    private fun initFab() {
        binding.fab.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Enter Heart Data")

            val dialogBinding = FabDialogBinding.inflate(LayoutInflater.from(applicationContext))
            builder.setView(dialogBinding.root)

            builder.setPositiveButton(getString(R.string.add)) { dialog, which ->
                viewModel.saveHeartData(
                    HeartData(
                        0,
                        System.currentTimeMillis(),
                        parseInt(dialogBinding.maxBloodPressure.text.toString()),
                        parseInt(dialogBinding.minBloodPressure.text.toString()),
                        parseInt(dialogBinding.heartRate.text.toString()),
                    )
                )
            }

            builder.setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            builder.show()
        }
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
    }
}