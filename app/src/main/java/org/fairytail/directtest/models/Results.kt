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
        val correct: Boolean
)

@Parcel(Parcel.Serialization.BEAN)
data class TestResult @ParcelConstructor constructor(
        val test: Test,
        val questionResults: List<QuestionResult>,
        val student: Student
)