package com.karansyd4.pokedex.di

import android.content.Context
import androidx.room.Room
import com.karansyd4.pokedex.data.local.AppDatabase
import com.karansyd4.pokedex.data.local.PokedexDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(appDatabase: AppDatabase): PokedexDAO {
        return appDatabase.pokedexDAO()
    }
}