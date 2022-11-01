package com.example.nisumtest.core.di

import com.example.nisumtest.data.datasource.DataSourcePokemonApiImpl
import com.example.nisumtest.data.datasource.interfaces.DataSourcePokemonApi
import com.example.nisumtest.data.repositories.RepositoryPokemonApiImpl
import com.example.nisumtest.data.repositories.interfaces.RepositoryPokemonApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindRepositoryPokemonApi(
        repositoryPokemonApiImpl: RepositoryPokemonApiImpl,
    ): RepositoryPokemonApi

    @Binds
    @Singleton
    abstract fun bindDataSourcePokemonApi(
        dataSourcePokemonApi: DataSourcePokemonApiImpl,
    ): DataSourcePokemonApi
}