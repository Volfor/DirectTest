package org.fairytail.directtest.models

import io.realm.RealmObject

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
data class Answer(
        val text: String,
        val checked: Boolean = false
) {
    constructor(answer: RealmAnswer) : this(answer.text!!, answer.checked!!)
    constructor(number: Number) : this(number.toString())
}

open class RealmAnswer(
        open var text: String? = null,
        open var checked: Boolean? = null
) : RealmObject()