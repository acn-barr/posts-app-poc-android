package com.bjcc.posts.core.di

import android.app.Application
import androidx.room.Room
import com.bjcc.posts.core.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application) = Room.databaseBuilder(
        app.applicationContext,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).build()
}