package com.example.officialmusicapp.di

import android.content.Context
import androidx.room.Room
import com.example.officialmusicapp.data.database.MusicDatabase
import com.example.officialmusicapp.data.model.daos.SongDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMusicDatabase(@ApplicationContext context: Context): MusicDatabase {
        return Room.databaseBuilder(
            context,
            MusicDatabase::class.java,
            "music database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideSongDao(database: MusicDatabase): SongDao {
        return database.songDao()
    }
}