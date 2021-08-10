package com.karansyd4.pokedex.di

import android.content.Context
import androidx.room.Room
import com.karansyd4.pokedex.data.local.PokedexDAO
import com.karansyd4.pokedex.data.local.PokedexDatabase
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
    fun provideBlogDb(@ApplicationContext context: Context): PokedexDatabase {
        return Room.databaseBuilder(
            context, PokedexDatabase::class.java,
            PokedexDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(pokedexDatabase: PokedexDatabase): PokedexDAO {
        return pokedexDatabase.pokedexDAO()
    }
}