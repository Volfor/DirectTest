package org.fairytail.directtest.models

import android.databinding.ObservableBoolean
import io.realm.RealmObject
import org.parceler.Parcel
import org.parceler.ParcelConstructor

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
@Parcel(Parcel.Serialization.BEAN)
data class Answer @ParcelConstructor constructor(
        val text: String,
        var checked: Boolean = false
) {
    constructor(answer: RealmAnswer) : this(answer.text!!, answer.checked!!)
    constructor(number: Number) : this(number.toString())

    val observableState = ObservableBoolean(checked)
}

open class RealmAnswer(
        open var text: String? = null,
        open var checked: Boolean? = null
) : RealmObject()