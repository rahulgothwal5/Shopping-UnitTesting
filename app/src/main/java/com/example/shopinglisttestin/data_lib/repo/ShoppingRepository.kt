package com.example.shopinglisttestin.data_lib.repo

import androidx.lifecycle.LiveData
import com.androiddevs.shoppinglisttestingyt.data.remote.responses.ImageResponse
import com.example.shopinglisttestin.data_lib.data_source.local.entity.ShoppingItem
import com.example.shopinglisttestin.data_lib.utils.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}