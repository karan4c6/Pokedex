package com.karansyd4.pokedex.di

import com.karansyd4.pokedex.data.local.PokedexDAO
import com.karansyd4.pokedex.data.remote.PokedexService
import com.karansyd4.pokedex.data.repository.PokedexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun providePokedexRepository(
        pokedexDAO: PokedexDAO,
        pokedexService: PokedexService
    ): PokedexRepository {
        return PokedexRepository(pokedexDAO, pokedexService)
    }
}