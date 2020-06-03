package com.apipas.mynote.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.apipas.mynote.event.NavToDirection
import com.apipas.mynote.util.Log
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_note_list.*
import kotlin.reflect.KClass


abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    layoutId: Int,
    viewModelClass: KClass<VM>
) :
    MvvmFragment<B, VM>(layoutId, viewModelClass) {

    companion object {
        const val DIALOG_ERROR = -1
    }

//    val activityViewModel: MainVM by sharedViewModel(MainVM::class)// todo be added

    private var rootLayout: View? = null

    open val requiresAuthentication = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        rootLayout = view?.let { it.rootView }
        subscribers()
        return view
    }

    private fun subscribers() {
        subscribe(NavToDirection::class, Observer {
            findNavController().navigate(it.navDirections)
        })
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


    open fun initProgress() {
        // show the loading when [MainViewModel.loading] is true
        viewModel.loading.observe(viewLifecycleOwner, Observer { value ->
            value?.let { show ->
                Log.d("show $value ")
            }
        })

    }

    open fun showProgress(show: Boolean) {
        // to be override in sub-fragment to show/hide loading
    }
}