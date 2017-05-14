package org.fairytail.directtest.screens.testcreation

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.os.Bundle
import android.widget.RadioGroup
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import kotlinx.android.synthetic.main.activity_new_test.*
import mobi.upod.timedurationpicker.TimeDurationPickerDialog
import org.fairytail.directtest.BR
import org.fairytail.directtest.Db
import org.fairytail.directtest.R
import org.fairytail.directtest.base.BaseBoundActivity
import org.fairytail.directtest.databinding.ActivityNewTestBinding
import org.fairytail.directtest.databinding.ItemQuestionEditBinding
import org.fairytail.directtest.models.*
import org.jetbrains.anko.onCheckedChange
import org.jetbrains.anko.onClick

/**
 * Created by Valentine on 5/14/2017.
 */


class NewTestActivity : BaseBoundActivity<ActivityNewTestBinding>(R.layout.activity_new_test) {
    val defaultTime: Long = 15 * 60 * 1000

    val realmQuestion = RealmQuestion()

    val questionList = ObservableArrayList<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val test = realm.createObject(RealmTest::class.java, Db.randomUuidString())

        val timePickerDialog = TimeDurationPickerDialog(this, TimeDurationPickerDialog.OnDurationSetListener { view, duration ->
//            toast("time selected: $duration")
        }, defaultTime)

        questionList.add(Question(QuestionType.SINGLE_ANSWER, "", null, listOf<Answer>()))

        btnSetTime.onClick {
            timePickerDialog.show()
        }

        btnAddQuestion.onClick { }

        LastAdapter(questionList, BR.item)
                .type { _, _ ->
                    Type<ItemQuestionEditBinding>(R.layout.item_question_edit)
                            .onBind {


                                it.binding.item.answerList[0].text
                            }
                }
                .into(questionsRecycler)
    }

    fun save() {

    }
}

class QuestionEditViewModel : BaseObservable() {
    val listVisibility = ObservableBoolean(true)
    val inputVisibility = ObservableBoolean(false)

    val questionText = ObservableField<String>()

    val answerList = ObservableArrayList<Answer>()

    init {
        initAnswerList()
    }

    fun setOnCheckedChangeListener(radioGroup: RadioGroup) {
        radioGroup.onCheckedChange { _, id ->
            when (id) {
                R.id.singleType -> {
                    initAnswerList()
                    showAnswerList()
                }
                R.id.multipleType -> {
                    initAnswerList()
                    showAnswerList()
                }
                R.id.inputType -> showInputField()
            }
        }
    }

    fun initAnswerList() {
        answerList.clear()
        for (i in 0..2) {
            answerList.add(Answer(""))
        }
    }

    fun addEmptyAnswer() {
        answerList.add(Answer(""))
    }

    fun showAnswerList() {
        listVisibility.set(true)
        inputVisibility.set(false)
    }

    fun showInputField() {
        listVisibility.set(false)
        inputVisibility.set(true)
    }
}