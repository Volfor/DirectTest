package org.fairytail.directtest.screens.student.passing

import android.os.Bundle
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import kotlinx.android.synthetic.main.activity_passing.*
import kotlinx.android.synthetic.main.activity_test_list.*
import org.fairytail.directtest.BR
import org.fairytail.directtest.R
import org.fairytail.directtest.base.BaseBoundActivity
import org.fairytail.directtest.databinding.*
import org.fairytail.directtest.models.Question
import org.fairytail.directtest.models.QuestionType.*
import org.jetbrains.anko.toast

/**
 * Created by Valentine on 5/13/2017.
 */
class PassingActivity : BaseBoundActivity<ActivityPassingBinding>(R.layout.activity_passing) {
    private val TYPE_SINGLE_ANSWER = Type<QuestionSingleAnswerBinding>(R.layout.question_single_answer).onBind {
        LastAdapter(it.binding.item.answers, BR.item)
                .type { _, _ ->
                    Type<ItemSingleAnswerBinding>(R.layout.item_single_answer)
                            .onClick { toast("Clicked #${it.adapterPosition}: ${it.binding.item}") }
                }
                .into(it.binding.recycler)
    }
    private val TYPE_MULTIPLE_ANSWERS = Type<QuestionMultipleAsnwersBinding>(R.layout.question_multiple_asnwers).onBind {
        LastAdapter(it.binding.item.answers, BR.item)
                .type { _, _ ->
                    Type<ItemMultipleAnswersBinding>(R.layout.item_multiple_answers)
                            .onClick { toast("Clicked #${it.adapterPosition}: ${it.binding.item}") }
                }
                .into(it.binding.recycler)
    }
    private val TYPE_INPUT_NUMBER = Type<QuestionNumberAnswerBinding>(R.layout.question_number_answer)
    private val TYPE_INPUT_TEXT = Type<QuestionTextAnswerBinding>(R.layout.question_text_answer)

    val questionList = listOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LastAdapter(questionList, BR.item).type { item, _ ->
            when ((item as Question).type) {
                SINGLE_ANSWER -> TYPE_SINGLE_ANSWER
                MULTIPLE_ANSWERS -> TYPE_MULTIPLE_ANSWERS
                INPUT_NUMBER -> TYPE_INPUT_NUMBER
                INPUT_TEXT -> TYPE_INPUT_TEXT
            }
        }.into(question_list)
    }
}