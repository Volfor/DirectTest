package org.fairytail.directtest.models

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
enum class QuestionType {
    SINGLE_ANSWER, MULTIPLE_ANSWERS, INPUT_NUMBER, INPUT_TEXT
}

data class QuestionImage(val url: String) {
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

data class Question(
        val type: QuestionType,
        val text: String,
        val image: QuestionImage?, // TODO: save pi
        val answers: List<Answer>,
        private val correctAnswers: List<Answer>
) {
    constructor(question: RealmQuestion) : this(
            QuestionType.valueOf(question.type!!),
            question.text!!,
            QuestionImage.from(question.image),
            question.answers!!.map { Answer(it) },
            question.correctAnswers!!.map { Answer(it) }
    )

    fun check(vararg answers: Answer): Boolean {
        return correctAnswers.containsAll(answers.toList()) && correctAnswers.size == answers.size
    }
}

open class RealmQuestion(
        open var type: String? = null,
        open var text: String? = null,
        open var image: RealmQuestionImage? = null,
        open var answers: RealmList<RealmAnswer>? = null,
        open var correctAnswers: RealmList<RealmAnswer>? = null
) : RealmObject() {
    var typeEnum: QuestionType
        get() = QuestionType.valueOf(type!!)
        set(value) {
            type = value.toString()
        }
}
