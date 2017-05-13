package org.fairytail.directtest

import io.realm.Realm
import io.realm.RealmModel
import java.util.*
import kotlin.reflect.KClass

/**
 * Created by Alex on 12/29/2016.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */

object Db {
    fun randomUuidString(): String {
        return UUID.randomUUID().toString()
    }
}