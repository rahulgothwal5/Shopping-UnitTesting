package com.example.shopinglisttestin.data_lib.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.shopinglisttestin.data_lib.data_source.local.ShoppingDB
import com.example.shopinglisttestin.data_lib.data_source.local.dao.ShoppingDao
import com.example.shopinglisttestin.data_lib.data_source.local.entity.ShoppingItem
import com.example.shopinglisttestin.getOrAwaitValue
import com.example.shopinglisttestin.launchFragmentInHiltContainer
import com.example.shopinglisttestin.ui.fragment.ShoppingFragment
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi

@SmallTest
//@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ShoppingItemDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var shoppingDB: ShoppingDB

    lateinit var shoppingDao: ShoppingDao

    @Before
    fun setup() {
        hiltRule.inject()
        shoppingDao = shoppingDB.shoppingDao()
    }

    @After
    fun tearDown() {
        shoppingDB.close()
    }

    @Test
    fun insertShoppingItem() {
        runTest {
            val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
            shoppingDao.insertShoppingItem(shoppingItem)
            val result = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
            assertThat(result).contains(shoppingItem)
        }
    }


    @Test
    fun deleteShoppingItem() {
        runTest {
            val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
            shoppingDao.deleteShoppingItem(shoppingItem)
            val result = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
            assertThat(result).doesNotContain(shoppingItem)
        }
    }

    @Test
    fun observeTotalPriceSum() {
        runTest {
            val shoppingItem1 = ShoppingItem("name1", 2, 6f, "url", id = 1)
            val shoppingItem2 = ShoppingItem("name2", 1, 9f, "url", id = 2)
            val shoppingItem3 = ShoppingItem("name3", 6, 5f, "url", id = 3)
            shoppingDao.insertShoppingItem(shoppingItem1)
            shoppingDao.insertShoppingItem(shoppingItem2)
            shoppingDao.insertShoppingItem(shoppingItem3)
            val result = shoppingDao.observeTotalPrice().getOrAwaitValue()
            assertThat(result).isEqualTo(shoppingItem1.amount * shoppingItem1.price + shoppingItem2.amount * shoppingItem2.price + shoppingItem3.amount * shoppingItem3.price)
        }
    }

    @Test
    fun testLaunchFragmentInHiltContainer(){
        launchFragmentInHiltContainer<ShoppingFragment> {

        }
    }

}