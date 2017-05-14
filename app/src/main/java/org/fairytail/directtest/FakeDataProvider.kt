package org.fairytail.directtest

import io.realm.Realm
import org.fairytail.directtest.models.QuestionType
import org.fairytail.directtest.models.RealmAnswer
import org.fairytail.directtest.models.RealmQuestion
import org.fairytail.directtest.models.RealmTest
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */

object FakeDataProvider {
    fun provideFakeTests() {
        Realm.getDefaultInstance().use { r ->
            r.executeTransaction { realm ->
                realm.deleteAll()

                (0..25).forEach {
                    val test = realm.createObject(RealmTest::class.java, Db.randomUuidString())
                    test.name = "Test "+it
                    test.time = TimeUnit.MINUTES.toMillis(25)
                    (0..Random().nextInt(15)).forEach {
                        val question = realm.createObject(RealmQuestion::class.java)
                        question.typeEnum = QuestionType.values()[Random().nextInt(QuestionType.values().size)]
                        question.text = "Test question $it"
                        createAnswers(realm, question)
                        test.questions!!.add(question)
                    }
                }
            }
        }
    }

    private fun createAnswers(realm: Realm, question: RealmQuestion) {
        val answers: MutableList<RealmAnswer> = when (question.typeEnum) {
            QuestionType.SINGLE_ANSWER -> {
                val answers = mutableListOf<RealmAnswer>()

                val correctAnswer = realm.createObject(RealmAnswer::class.java)
                correctAnswer.text = "Correct"
                correctAnswer.checked = false
                correctAnswer.isCorrect = true

                answers += correctAnswer

                (2..2 + Random().nextInt(5)).forEach {
                    val incorrectAnswer = realm.createObject(RealmAnswer::class.java)
                    incorrectAnswer.text = "Incorrect $it"
                    incorrectAnswer.checked = false
                    incorrectAnswer.isCorrect = false
                    answers += incorrectAnswer
                }
                answers
            }
            QuestionType.MULTIPLE_ANSWERS -> {
                val answers = mutableListOf<RealmAnswer>()
                (2..2 + Random().nextInt(4)).forEach {
                    val correctAnswer = realm.createObject(RealmAnswer::class.java)
                    correctAnswer.text = "Correct $it"
                    correctAnswer.checked = false
                    correctAnswer.isCorrect = true
                    answers += correctAnswer
                }

                (2..2 + Random().nextInt(4)).forEach {
                    val answer = realm.createObject(RealmAnswer::class.java)
                    answer.text = "Incorrect $it"
                    answer.checked = false
                    answer.isCorrect = false
                    answers += answer
                }

                answers
            }
            QuestionType.INPUT_NUMBER -> {
                val correctAnswer = realm.createObject(RealmAnswer::class.java)
                correctAnswer.text = "42"
                correctAnswer.checked = false
                correctAnswer.isCorrect = true

                val userAnswer = realm.createObject(RealmAnswer::class.java)
                userAnswer.text = ""
                userAnswer.checked = false
                userAnswer.isCorrect = false

                mutableListOf(correctAnswer, userAnswer)
            }
            QuestionType.INPUT_TEXT -> {
                val correctAnswer = realm.createObject(RealmAnswer::class.java)
                correctAnswer.text = "Correct"
                correctAnswer.checked = false
                correctAnswer.isCorrect = true

                val userAnswer = realm.createObject(RealmAnswer::class.java)
                userAnswer.text = ""
                userAnswer.checked = false
                userAnswer.isCorrect = false

                mutableListOf(correctAnswer, userAnswer)
            }
        }

        question.answers!!.addAll(answers)
    }
}