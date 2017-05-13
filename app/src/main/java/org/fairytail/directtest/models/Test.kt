package org.fairytail.directtest.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
data class Test(
        val id: UUID,
        val name: String,
        val questions: List<Question>,
        val time: Long
) {
    constructor(test: RealmTest) : this(
            UUID.fromString(test.id),
            test.name!!,
            test.questions!!.map { Question(it) },
            test.time!!
    )
}

open class RealmTest(
        @PrimaryKey open var id: String? = null,
        open var name: String? = null,
        open var questions: RealmList<RealmQuestion>? = null,
        open var time: Long? = null
) : RealmObject()