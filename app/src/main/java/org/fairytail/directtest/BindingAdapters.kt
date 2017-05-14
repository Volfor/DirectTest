package org.fairytail.directtest

import android.databinding.BindingAdapter
import android.databinding.BindingConversion
import android.graphics.Typeface
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView

/**
 * Created by Valentine on 5/14/2017.
 */
@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int {
    return if (visible) VISIBLE else GONE
}

@BindingAdapter("font")
fun setFont(textView: TextView, fontName: String) {
    textView.typeface = Typeface.createFromAsset(textView.context.assets, "fonts/$fontName.ttf")
}