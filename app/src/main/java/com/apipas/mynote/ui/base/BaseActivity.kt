package com.apipas.mynote.ui.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import kotlin.reflect.KClass

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes layoutId: Int, viewModelClass: KClass<VM>
) : MvvmActivity<B, VM>(layoutId, viewModelClass)