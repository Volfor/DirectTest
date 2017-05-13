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

data class Question(
        val type: QuestionType,
        val answers: List<Answer>,
        val correctAnswers: List<Answer>
) {
    constructor(question: RealmQuestion) : this(
            QuestionType.valueOf(question.type),
            question.answers.map { Answer(it) },
            question.correctAnswers.map { Answer(it) }
    )

    fun check(vararg answers: Answer): Boolean {
        return correctAnswers.containsAll(answers.toList()) && correctAnswers.size == answers.size
    }
}

open class RealmQuestion(
        val type: String,
        val answers: RealmList<RealmAnswer>,
        val correctAnswers: RealmList<RealmAnswer>
) : RealmObject()
