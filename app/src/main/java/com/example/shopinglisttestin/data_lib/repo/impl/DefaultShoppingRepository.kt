package com.example.shopinglisttestin.data_lib.repo.impl

import android.util.Log
import androidx.lifecycle.LiveData
import com.androiddevs.shoppinglisttestingyt.data.remote.responses.ImageResponse
import com.example.shopinglisttestin.data_lib.data_source.local.dao.ShoppingDao
import com.example.shopinglisttestin.data_lib.data_source.local.entity.ShoppingItem
import com.example.shopinglisttestin.data_lib.data_source.remote.service.PixaBayService
import com.example.shopinglisttestin.data_lib.repo.ShoppingRepository
import com.example.shopinglisttestin.data_lib.utils.Resource
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixaBayService
) : ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Log.e("EXCEPTION", "EXCEPTION:", e)
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}














