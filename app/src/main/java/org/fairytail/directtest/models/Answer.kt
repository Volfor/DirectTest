package org.fairytail.directtest.models

import io.realm.RealmObject

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
data class Answer(
        val text: String
) {
    constructor(answer: RealmAnswer) : this(answer.text)
    constructor(number: Number) : this(number.toString())
}

open class RealmAnswer(val text: String) : RealmObject()