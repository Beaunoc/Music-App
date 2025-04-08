package com.example.officialmusicapp.utils

import android.annotation.SuppressLint

object FormatDuration {
    @SuppressLint("DefaultLocale")
    fun formatDuration(duration: Long): String {
        val minutes = (duration / 60).toInt()
        val seconds = (duration % 60).toInt()
        return String.format("%02d:%02d", minutes, seconds)
    }
}