package com.example.officialmusicapp.data.repository

import com.example.officialmusicapp.data.model.daos.SongDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongRepository @Inject constructor(
    private val songDao: SongDao,
){
}