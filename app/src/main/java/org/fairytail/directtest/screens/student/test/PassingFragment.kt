package org.fairytail.directtest.screens.student.test

import android.os.Bundle
import android.view.View
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import kotlinx.android.synthetic.main.fragment_passing.*
import org.fairytail.directtest.BR
import org.fairytail.directtest.R
import org.fairytail.directtest.databinding.*
import org.fairytail.directtest.models.Question
import org.fairytail.directtest.models.QuestionType
import org.fairytail.directtest.screens.student.BaseBoundStudentTestFragment
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Valentine on 5/13/2017.
 */
class PassingFragment : BaseBoundStudentTestFragment<FragmentPassingBinding>(R.layout.fragment_passing) {
    private val TYPE_SINGLE_ANSWER = Type<QuestionSingleAnswerBinding>(R.layout.question_single_answer).onBind {
        val question = it.binding.item

        LastAdapter(question.answers, BR.item)
                .type { _, _ ->
                    Type<ItemSingleAnswerBinding>(R.layout.item_single_answer)
                            .onClick {
                                it.binding.radioButton.setOnCheckedChangeListener { _, _ -> }
                                question.answers.forEach { it.checked = false; it.observableState.set(false) }

                                it.binding.item.checked = true
                                it.binding.item.observableState.set(true)
                                toast("Clicked #${it.adapterPosition}: ${it.binding.item}")
                            }
                }
                .into(it.binding.recycler)
    }
    private val TYPE_MULTIPLE_ANSWERS = Type<QuestionMultipleAsnwersBinding>(R.layout.question_multiple_asnwers).onBind {
        val question = it.binding.item

        LastAdapter(question.answers, BR.item)
                .type { _, _ ->
                    Type<ItemMultipleAnswersBinding>(R.layout.item_multiple_answers)
                            .onClick {
                                it.binding.item.checked = !it.binding.item.checked
                                it.binding.item.observableState.set(it.binding.item.checked)
                                toast("Clicked #${it.adapterPosition}: ${it.binding.item}")
                            }
                }
                .into(it.binding.recycler)
    }
    private val TYPE_INPUT_NUMBER = Type<QuestionNumberAnswerBinding>(R.layout.question_number_answer)
    private val TYPE_INPUT_TEXT = Type<QuestionTextAnswerBinding>(R.layout.question_text_answer)


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LastAdapter(test.questions, BR.item).type { item, _ ->
            when ((item as Question).type) {
                QuestionType.SINGLE_ANSWER -> TYPE_SINGLE_ANSWER
                QuestionType.MULTIPLE_ANSWERS -> TYPE_MULTIPLE_ANSWERS
                QuestionType.INPUT_NUMBER -> TYPE_INPUT_NUMBER
                QuestionType.INPUT_TEXT -> TYPE_INPUT_TEXT
            }
        }.into(questionsRecycler)
    }

}
