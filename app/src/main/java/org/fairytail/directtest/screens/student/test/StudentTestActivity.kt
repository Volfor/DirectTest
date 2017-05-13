package org.fairytail.directtest.screens.student.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.fairytail.directtest.Prefs
import org.fairytail.directtest.R
import org.fairytail.directtest.WifiToggleHelper
import org.fairytail.directtest.base.BaseBoundSalutActivity
import org.fairytail.directtest.databinding.ActivityStudentTestBinding
import org.fairytail.directtest.models.Test

/**
 * Created by Valentine on 5/13/2017.
 */

enum class State { INITIAL, SELECT_TEACHER, AWAIT_START, QUIZ, RESULT }

class StudentTestActivity : BaseBoundSalutActivity<ActivityStudentTestBinding>(R.layout.activity_student_test, false) {
    private var state: State = State.INITIAL

    override val deviceName = Prefs.studentInfo.name

    lateinit var test: Test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moveToState(State.SELECT_TEACHER)
    }

    fun moveToState(state: State, intent: Intent? = null) {
        if (this.state == state) return

        supportFragmentManager.beginTransaction()
                .replace(R.id.placeholder, when(state) {
                    State.SELECT_TEACHER -> SelectTeacherFragment()
                    State.AWAIT_START -> TODO()
                    State.QUIZ -> TODO()
                    State.RESULT -> TODO()
                    else -> throw IllegalStateException()
                })
                .commit()
        this.state = state
    }

    override fun onDataReceived(p0: Any?) {
        TODO("not implemented")
    }

    companion object {
        fun start(ctx: Context) {
            WifiToggleHelper.toggle()
                    .subscribe {
                        ctx.startActivity(Intent(ctx, StudentTestActivity::class.java))
                    }
        }
    }
}