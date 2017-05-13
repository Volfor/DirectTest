package org.fairytail.directtest.models

import io.reactivex.Observable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.fairytail.directtest.Db
import java.util.*

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
data class Test(
        val id: UUID,
        val questions: List<Question>,
        val time: Long
) {
    companion object {
        // TODO: this
        fun getAll(): Observable<List<Test>> {
            return Observable.just(emptyList())
        }
    }
}

open class RealmTest(
        @PrimaryKey open var id: String = Db.randomUuidString(),
        val questions: List<Question>,
        val time: Long
) : RealmObject()