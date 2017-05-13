package org.fairytail.directtest.screens.student.passing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import kotlinx.android.synthetic.main.activity_passing.*
import org.fairytail.directtest.BR
import org.fairytail.directtest.R
import org.fairytail.directtest.base.BaseBoundActivity
import org.fairytail.directtest.databinding.*
import org.fairytail.directtest.models.Question
import org.fairytail.directtest.models.QuestionType.*
import org.fairytail.directtest.models.Test
import org.jetbrains.anko.toast
import org.parceler.Parcels

/**
 * Created by Valentine on 5/13/2017.
 */
class PassingActivity : BaseBoundActivity<ActivityPassingBinding>(R.layout.activity_passing) {
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

    private lateinit var test: Test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        test = Parcels.unwrap(intent.extras.getParcelable<Parcelable>(EXTRA_TEST))

        LastAdapter(test.questions, BR.item).type { item, _ ->
            when ((item as Question).type) {
                SINGLE_ANSWER -> TYPE_SINGLE_ANSWER
                MULTIPLE_ANSWERS -> TYPE_MULTIPLE_ANSWERS
                INPUT_NUMBER -> TYPE_INPUT_NUMBER
                INPUT_TEXT -> TYPE_INPUT_TEXT
            }
        }.into(questionsRecycler)
    }

    companion object {
        private val EXTRA_TEST = "EXTRA_TEST"
        fun start(ctx: Context, test: Test) {
            ctx.startActivity(Intent(ctx, PassingActivity::class.java).putExtra(EXTRA_TEST, Parcels.wrap(test)))
        }
    }
}