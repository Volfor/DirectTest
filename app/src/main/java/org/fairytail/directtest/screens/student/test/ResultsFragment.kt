package org.fairytail.directtest.screens.student.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import kotlinx.android.synthetic.main.activity_test_result.*
import org.fairytail.directtest.BR
import org.fairytail.directtest.R
import org.fairytail.directtest.base.BaseBoundActivity
import org.fairytail.directtest.databinding.ActivityTestResultBinding
import org.fairytail.directtest.databinding.ItemQuestionResultCellBinding
import org.fairytail.directtest.models.TestResult
import org.parceler.Parcels

/**
 * Created by Valentine on 5/13/2017.
 */
class TestResultActivity : BaseBoundActivity<ActivityTestResultBinding>(R.layout.activity_test_result) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val testResult = Parcels.unwrap<TestResult>(intent.getParcelableExtra(EXTRA_TEST_RESULT))

        LastAdapter(testResult.questionResults, BR.item)
                .type { _, position ->
                    Type<ItemQuestionResultCellBinding>(R.layout.item_question_result_cell)
                            .onBind {
                                it.binding.questionNumber.text = position.toString()

                                var color = R.color.material_color_grey_400
                                if (it.binding.item.correct) {
                                    color = R.color.material_color_green_400
                                } else if (!it.binding.item.answers.isEmpty()) {
                                    color = R.color.material_color_red_400
                                }

                                it.binding.cardView.setCardBackgroundColor(getColor(color))
                            }
                            .onClick {
                                TODO("start detailed results activity")
                            }
                }
                .into(questionsRecycler)
    }

    companion object {
        private val EXTRA_TEST_RESULT = "EXTRA_TEST_RESULT"

        fun start(ctx: Context, testResult: TestResult) {
            ctx.startActivity(Intent(ctx, TestResultActivity::class.java).putExtra(EXTRA_TEST_RESULT, Parcels.wrap(testResult)))
        }
    }

}
