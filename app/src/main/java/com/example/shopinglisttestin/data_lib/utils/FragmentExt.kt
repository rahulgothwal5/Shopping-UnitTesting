package com.example.shopinglisttestin.data_lib.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

fun <T : ViewModel> Fragment.obtainViewModel(
    owner: ViewModelStoreOwner,
    viewModelClass: Class<T>,
    viewmodelFactory: ViewModelProvider.Factory
) =
    ViewModelProvider(owner, viewmodelFactory).get(viewModelClass)