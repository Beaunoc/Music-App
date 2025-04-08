package com.example.officialmusicapp.utils

import android.annotation.SuppressLint

object FormatDuration {
    @SuppressLint("DefaultLocale")
    fun formatDuration(duration: Long): String {
        val totalSeconds = duration / 1000
        val minutes = (totalSeconds / 60).toInt()
        val seconds = (totalSeconds % 60).toInt()
        return String.format("%02d:%02d", minutes, seconds)
    }
}