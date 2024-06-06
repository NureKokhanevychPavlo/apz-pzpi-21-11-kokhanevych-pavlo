package com.animal.hotel.presentation.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

typealias FunctionForSnackbar = () -> Unit
private const val DURATION_OF_SNACKBAR = 1000

object SnackbarManager {

    fun showSnackbar(view: View?, label: String, functionForDismissing: FunctionForSnackbar = { Unit }) {
        view?.let {
            Snackbar.make(
                it,
                label,
                DURATION_OF_SNACKBAR
            )
        }?.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                functionForDismissing()
            }
        })?.show()
    }
}