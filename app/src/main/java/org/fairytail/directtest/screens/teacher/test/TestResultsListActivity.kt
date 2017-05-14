package org.fairytail.directtest.screens.teacher.test

import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.os.Bundle
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import kotlinx.android.synthetic.main.activity_test_results_list.*
import org.fairytail.directtest.BR
import org.fairytail.directtest.R
import org.fairytail.directtest.base.BaseBoundActivity
import org.fairytail.directtest.databinding.ActivityTestResultsListBinding
import org.fairytail.directtest.databinding.ItemStudentBinding
import org.fairytail.directtest.models.Student
import org.fairytail.directtest.models.TestResult
import org.fairytail.directtest.models.TestResults
import org.fairytail.directtest.screens.student.test.TestResultActivity
import org.parceler.Parcels

/**
 * Created by Valentine on 5/13/2017.
 */
class TestResultsListActivity : BaseBoundActivity<ActivityTestResultsListBinding>(R.layout.activity_test_results_list) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val testResults = Parcels.unwrap<TestResults>(intent.getParcelableExtra(EXTRA_RESULTS))
        val students = mutableListOf<StudentItemViewModel>()
        testResults.items.forEach { students.add(StudentItemViewModel(it.student, it)) }

        LastAdapter(students, BR.item)
                .type { _, _ ->
                    Type<ItemStudentBinding>(R.layout.item_student)
                            .onClick { TestResultActivity.start(this, it.binding.item.results) }
                }
                .into(studentsRecycler)
    }

    companion object {
        private val EXTRA_RESULTS = "EXTRA_RESULTS"

        fun start(ctx: Context, results: TestResults) {
            ctx.startActivity(Intent(ctx, TestResultsListActivity::class.java).putExtra(EXTRA_RESULTS, Parcels.wrap(results)))
        }
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