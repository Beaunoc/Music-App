package com.example.officialmusicapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.officialmusicapp.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val songRepository: SongRepository,
) : ViewModel() {
}