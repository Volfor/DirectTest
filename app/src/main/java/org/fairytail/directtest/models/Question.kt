package org.fairytail.directtest.models

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
    fun check(vararg answers: Answer): Boolean {
        return correctAnswers.containsAll(answers.toList()) && correctAnswers.size == answers.size
    }
}

data class Answer(
        val text: String
) {
    constructor(number: Number) : this(number.toString())
}

open class RealmQuestion(
        val type: String,
        val answers: List<Answer>,
        val correctAnswers: List<Answer>
)