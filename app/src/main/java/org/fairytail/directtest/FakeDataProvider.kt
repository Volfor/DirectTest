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
        val correctAnswers: MutableList<RealmAnswer> = when (question.typeEnum) {
            QuestionType.SINGLE_ANSWER -> {
                val answer = realm.createObject(RealmAnswer::class.java)
                answer.text = "Correct"
                answer.checked = false
                mutableListOf(answer)
            }
            QuestionType.MULTIPLE_ANSWERS -> {
                val answers = mutableListOf<RealmAnswer>()
                (2..Random().nextInt(4)).forEach {
                    val answer = realm.createObject(RealmAnswer::class.java)
                    answer.text = "Correct"
                    answer.checked = false
                    answers += answer
                }
                answers
            }
            QuestionType.INPUT_NUMBER -> {
                val answer = realm.createObject(RealmAnswer::class.java)
                answer.text = "42"
                answer.checked = false
                mutableListOf(answer)
            }
            QuestionType.INPUT_TEXT -> {
                val answer = realm.createObject(RealmAnswer::class.java)
                answer.text = "Correct"
                answer.checked = false
                mutableListOf(answer)
            }
        }
        val incorrectAnswers: MutableList<RealmAnswer> = when(question.typeEnum) {
            QuestionType.SINGLE_ANSWER -> {
                val answers = mutableListOf<RealmAnswer>()
                (2..Random().nextInt(5)).forEach {
                    val answer = realm.createObject(RealmAnswer::class.java)
                    answer.text = "Incorrect"
                    answer.checked = false
                    answers += answer
                }
                answers
            }
            QuestionType.MULTIPLE_ANSWERS -> {
                val answers = mutableListOf<RealmAnswer>()
                (2..Random().nextInt(4)).forEach {
                    val answer = realm.createObject(RealmAnswer::class.java)
                    answer.text = "Incorrect"
                    answer.checked = false
                    answers += answer
                }
                answers
            }
            QuestionType.INPUT_NUMBER -> {
                val answer = realm.createObject(RealmAnswer::class.java)
                answer.text = ""
                answer.checked = false
                mutableListOf(answer)
            }
            QuestionType.INPUT_TEXT -> {
                val answer = realm.createObject(RealmAnswer::class.java)
                answer.text = ""
                answer.checked = false
                mutableListOf(answer)
            }
        }

        val allAnswers = correctAnswers + incorrectAnswers
        Collections.shuffle(allAnswers)

        question.answers!!.addAll(allAnswers)
    }
}