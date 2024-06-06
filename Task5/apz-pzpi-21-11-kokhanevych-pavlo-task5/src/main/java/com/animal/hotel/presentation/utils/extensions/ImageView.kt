package com.animal.hotel.presentation.utils.extensions

import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import com.animal.hotel.R
import com.bumptech.glide.Glide

fun ImageView.downloadAndPutPhoto(
    link: String? = null
) {
        Glide
            .with(this.context)
            .load(link)
            .circleCrop()
            .placeholder(R.drawable.ic_avatar)
            .error(R.drawable.ic_avatar)
            .into(this)
}

fun EditText.addTextListener( validationFunction: (String) -> Boolean, errorMessage: String) {
    with(this) {
        doOnTextChanged { text, _, _, _ ->
            error = if (!validationFunction(text.toString())) {
                errorMessage
            } else {
                null
            }
        }
    }
}