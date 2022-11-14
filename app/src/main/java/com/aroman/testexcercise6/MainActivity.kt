package com.aroman.testexcercise6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aroman.testexcercise6.data.FakeRepoImpl
import com.aroman.testexcercise6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isLoading = true
    private val viewModel = MainActivityViewModel(FakeRepoImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}