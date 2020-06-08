package com.apipas.mynote.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.apipas.mynote.ui.main.MainVM
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.reflect.KClass


abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    layoutId: Int,
    viewModelClass: KClass<VM>
) :
    MvvmFragment<B, VM>(layoutId, viewModelClass) {

    private val activityViewModel: MainVM by sharedViewModel(MainVM::class)

    private var rootLayout: View? = null

    open val requiresAuthentication = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        rootLayout = view?.let { it.rootView }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSnackBar()
    }

    open fun initSnackBar() {
        // Show a snackbar whenever the [ViewModel.snackbar] is updated a non-null value
        viewModel.snackbar.observe(viewLifecycleOwner, Observer { text ->
            text?.let {
                rootLayout?.let { it1 ->
                    Snackbar.make(it1, text, Snackbar.LENGTH_SHORT).show()
                    viewModel.resetSnackBar()
                }
            }
        })
    }
}