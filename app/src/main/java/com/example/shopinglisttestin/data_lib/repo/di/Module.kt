package com.example.shopinglisttestin.data_lib.repo.di

import com.example.shopinglisttestin.data_lib.data_source.local.dao.ShoppingDao
import com.example.shopinglisttestin.data_lib.data_source.remote.service.PixaBayService
import com.example.shopinglisttestin.data_lib.repo.ShoppingRepository
import com.example.shopinglisttestin.data_lib.repo.impl.DefaultShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideShoppingRepository(
        shoppingDao: ShoppingDao,
        pixabayAPI: PixaBayService
    ) = DefaultShoppingRepository(shoppingDao, pixabayAPI) as ShoppingRepository

}