package com.example.shopinglisttestin.ui.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.shopinglisttestin.R
import com.example.shopinglisttestin.data_lib.repo.FakeAndroidTestShoppingRepository
import com.example.shopinglisttestin.getOrAwaitValue
import com.example.shopinglisttestin.launchFragmentInHiltContainer
import com.example.shopinglisttestin.ui.adapter.ImageAdapter
import com.example.shopinglisttestin.ui.viewModel.ShoppingViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImagePickFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

//    lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Inject
    lateinit var fragmentFactory: TestShoppingFragmentFactory

    @Test
    fun clickImage_popBackStackAndSetImageUrl() {
        val navController = mock(NavController::class.java)
        val imageUrl = "TEST"
        val testViewModel = ShoppingViewModel(FakeAndroidTestShoppingRepository())
        launchFragmentInHiltContainer<ImagePickFragment>(
            fragmentFactory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
            imageAdapter.images = listOf(imageUrl)
            viewModel = testViewModel
        }

        onView(withId(R.id.rvImages)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageViewHolder>(0, ViewActions.click())
        )
        verify(navController).popBackStack()
        assertThat(testViewModel.curImageUrl.getOrAwaitValue()).isEqualTo(imageUrl)
    }


}