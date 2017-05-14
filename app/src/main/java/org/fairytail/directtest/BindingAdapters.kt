package org.fairytail.directtest

import android.databinding.BindingConversion
import android.view.View.GONE
import android.view.View.VISIBLE

/**
 * Created by Valentine on 5/14/2017.
 */
@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int {
    return if (visible) VISIBLE else GONE
}