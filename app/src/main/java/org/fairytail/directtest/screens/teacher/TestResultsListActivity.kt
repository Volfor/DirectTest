package org.fairytail.directtest.screens.teacher

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.os.Bundle
import org.fairytail.directtest.R
import org.fairytail.directtest.base.BaseBoundActivity
import org.fairytail.directtest.databinding.ActivityTestResultsListBinding
import org.fairytail.directtest.models.Student
import org.fairytail.directtest.models.TestResult

/**
 * Created by Valentine on 5/13/2017.
 */
class TestResultsListActivity : BaseBoundActivity<ActivityTestResultsListBinding>(R.layout.activity_test_results_list) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO: get testResults
//        val students = mutableListOf<StudentItemViewModel>()
//        testResults.forEach { students.add(StudentItemViewModel(it.student, it)) }
//
//        LastAdapter(students, BR.item)
//                .type { _, _ ->
//                    Type<ItemStudentBinding>(R.layout.item_student)
//                            .onClick {
//                                // TODO: open detailed results
//                            }
//                }
//                .into(studentsRecycler)
    }

}

class StudentItemViewModel(
        val student: Student,
        val results: TestResult
) : BaseObservable() {
    val correctCount: Int
        @Bindable get() {
            var count = 0
            results.questionResults.forEach { if (it.correct) count++ }
            return count
        }

    val questionsCount: Int
        @Bindable get() = results.test.questions.size

    val percentage: Int
        @Bindable get() = correctCount * 100 / questionsCount
}