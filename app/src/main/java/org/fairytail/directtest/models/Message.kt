package org.fairytail.directtest.models

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.Gson
import kotlin.reflect.KClass

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
enum class MessageType { START_TEST, TEST_RESULT }

@JsonObject
class Message(
        @JsonField
        var type: MessageType? = null,
        @JsonField
        var data: String? = null
) {
        fun <T : Any> getDataObject(klass: KClass<T>): T {
                return Gson().fromJson(data, klass.java)
        }
}