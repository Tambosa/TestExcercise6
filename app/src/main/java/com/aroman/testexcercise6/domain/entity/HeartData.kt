package com.aroman.testexcercise6.domain.entity

data class HeartData(
    val id: Int,
    val date: Long,
    val maxBloodPressure: Int,
    val minBloodPressure: Int,
    val heartRate: Int,
)