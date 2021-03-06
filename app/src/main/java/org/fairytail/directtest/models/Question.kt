package org.fairytail.directtest.models

import io.realm.RealmList
import io.realm.RealmObject
import org.parceler.Parcel
import org.parceler.ParcelConstructor

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
enum class QuestionType {
    SINGLE_ANSWER, MULTIPLE_ANSWERS, INPUT_NUMBER, INPUT_TEXT
}

@Parcel(Parcel.Serialization.BEAN)
data class QuestionImage @ParcelConstructor constructor(
        val url: String
) {
    companion object {
        fun from(image: RealmQuestionImage?) : QuestionImage? {
            if (image != null) {
                return QuestionImage(image.url!!)
            } else {
                return null
            }
        }
    }
}

open class RealmQuestionImage(open var url: String? = null) : RealmObject()

@Parcel(Parcel.Serialization.BEAN)
data class Question @ParcelConstructor constructor(
        val type: QuestionType,
        val text: String,
        val image: QuestionImage?, // TODO: save pi
        val answers: List<Answer>
) {
    constructor(question: RealmQuestion) : this(
            QuestionType.valueOf(question.type!!),
            question.text!!,
            QuestionImage.from(question.image),
            question.answers!!.map { Answer(it) }
    )

    val checkedAnswerTexts: List<String>
        get() = answers.filter { it.checked }.map { it.text }

    val correctAnswerTexts: List<String>
        get() = answers.filter { it.correct }.map { it.text }

    fun check(): Boolean {
        if (type == QuestionType.SINGLE_ANSWER || type == QuestionType.MULTIPLE_ANSWERS) {
            return correctAnswerTexts.containsAll(checkedAnswerTexts) && checkedAnswerTexts.containsAll(correctAnswerTexts)
        } else {
            return answers[0].text == answers[1].text
        }
    }
}

open class RealmQuestion(
        open var type: String? = null,
        open var text: String? = null,
        open var image: RealmQuestionImage? = null,
        open var answers: RealmList<RealmAnswer>? = null
) : RealmObject() {
    var typeEnum: QuestionType
        get() = QuestionType.valueOf(type!!)
        set(value) {
            type = value.toString()
        }
}
