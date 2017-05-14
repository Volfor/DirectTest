package org.fairytail.directtest.screens.student.test

import android.os.Bundle
import android.view.View
import com.github.ajalt.timberkt.e
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_results.*
import org.fairytail.directtest.BR
import org.fairytail.directtest.Prefs
import org.fairytail.directtest.R
import org.fairytail.directtest.databinding.FragmentResultsBinding
import org.fairytail.directtest.databinding.ItemQuestionResultCellBinding
import org.fairytail.directtest.models.Message
import org.fairytail.directtest.models.MessageType
import org.fairytail.directtest.models.TestResult
import org.fairytail.directtest.screens.student.BaseBoundStudentTestFragment

/**
 * Created by Valentine on 5/13/2017.
 */
class ResultsFragment : BaseBoundStudentTestFragment<FragmentResultsBinding>(R.layout.fragment_results) {
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val testResult = TestResult.from(Prefs.studentInfo, activity.test)

        network.sendToHost(Message(MessageType.TEST_RESULT, Gson().toJson(testResult)), { e { "Error sending test result" } })

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

                                it.binding.cardView.setCardBackgroundColor(activity.getColor(color))
                            }
                            .onClick {
                                TODO("start detailed results activity")
                            }
                }
                .into(questionsRecycler)
    }

}
