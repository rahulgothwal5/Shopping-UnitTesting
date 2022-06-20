package com.example.shopinglisttestin.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.shopinglisttestin.data_lib.repo.FakeAndroidTestShoppingRepository
import com.example.shopinglisttestin.ui.adapter.ImageAdapter
import com.example.shopinglisttestin.ui.adapter.ShoppingItemAdapter
import com.example.shopinglisttestin.ui.viewModel.ShoppingViewModel
import javax.inject.Inject

class TestShoppingFragmentFactory @Inject constructor(
    private val imageAdapter: ImageAdapter,
    private val glide: RequestManager,
    private val shoppingItemAdapter: ShoppingItemAdapter
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)
            AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment(glide)
            ShoppingFragment::class.java.name -> ShoppingFragment(
                shoppingItemAdapter,
                ShoppingViewModel(FakeAndroidTestShoppingRepository())
            )
            else -> super.instantiate(classLoader, className)
        }
    }
}