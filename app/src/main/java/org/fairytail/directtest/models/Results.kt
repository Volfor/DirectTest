package org.fairytail.directtest.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

/**
 * Created by Valentine on 5/13/2017.
 */
@Parcel(Parcel.Serialization.BEAN)
data class QuestionResult @ParcelConstructor constructor(
        val question: Question,
        val answers: List<Answer>,
        var correct: Boolean
)

@Parcel(Parcel.Serialization.BEAN)
data class TestResult @ParcelConstructor constructor(
        val test: Test,
        val questionResults: List<QuestionResult>,
        val student: Student
) {
    companion object {
        fun from(student: Student, test: Test): TestResult {
            val result = TestResult(test, test.questions.map { QuestionResult(it, it.answers, false) }, student)
            result.questionResults.forEach { it.correct = it.question.check() }
            return result
        }
    }
}

@Parcel(Parcel.Serialization.BEAN)
data class TestResults @ParcelConstructor constructor(
        val items: List<TestResult>
)