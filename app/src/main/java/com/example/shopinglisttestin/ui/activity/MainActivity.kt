package com.example.shopinglisttestin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopinglisttestin.R
import com.example.shopinglisttestin.ui.fragment.ShoppingFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: ShoppingFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
    }
}