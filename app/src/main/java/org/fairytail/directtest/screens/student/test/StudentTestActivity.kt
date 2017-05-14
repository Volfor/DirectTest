package org.fairytail.directtest.screens.student.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.ajalt.timberkt.e
import com.google.gson.Gson
import io.reactivex.Observable
import org.fairytail.directtest.Prefs
import org.fairytail.directtest.R
import org.fairytail.directtest.WifiToggleHelper
import org.fairytail.directtest.base.BaseBoundSalutActivity
import org.fairytail.directtest.databinding.ActivityStudentTestBinding
import org.fairytail.directtest.models.Message
import org.fairytail.directtest.models.MessageType
import org.fairytail.directtest.models.Test
import org.fairytail.directtest.models.TestResult
import java.util.concurrent.TimeUnit

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

        if (state == State.RESULT) {
            val testResult = TestResult.from(Prefs.studentInfo, test)
            network.sendToHost(Message(MessageType.TEST_RESULT, Gson().toJson(testResult)), { e { "Error sending test result" } })
            Observable.timer(3, TimeUnit.SECONDS)
                    .subscribe {
                        finish()
                        TestResultActivity.start(this, testResult)
                    }
            return
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.placeholder, when(state) {
                    State.SELECT_TEACHER -> SelectTeacherFragment()
                    State.AWAIT_START -> AwaitStartFragment()
                    State.QUIZ -> PassingFragment()
                    else -> AwaitStartFragment()
                })
                .commit()
        this.state = state
    }

    override fun onMessageReceived(msg: Message) {
        when (msg.type) {
            MessageType.START_TEST -> {
                test = msg.getDataObject(Test::class)
                moveToState(State.QUIZ)
            }
            MessageType.TEST_RESULT -> throw IllegalStateException()
            null -> throw IllegalStateException()
        }
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