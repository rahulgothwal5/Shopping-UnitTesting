package com.example.shopinglisttestin.data_lib.data_source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shopinglisttestin.data_lib.data_source.local.entity.ShoppingItem

@Dao
interface ShoppingDao {

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price * amount) FROM shopping_items")
    fun observeTotalPrice(): LiveData<Float>
}