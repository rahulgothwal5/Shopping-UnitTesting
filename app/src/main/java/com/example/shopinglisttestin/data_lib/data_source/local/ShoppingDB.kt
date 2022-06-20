package com.example.shopinglisttestin.data_lib.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopinglisttestin.data_lib.data_source.local.dao.ShoppingDao
import com.example.shopinglisttestin.data_lib.data_source.local.entity.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingDB : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao

}