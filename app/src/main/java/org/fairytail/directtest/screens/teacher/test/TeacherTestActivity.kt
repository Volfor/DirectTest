package org.fairytail.directtest.screens.teacher.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.fairytail.directtest.Prefs
import org.fairytail.directtest.R
import org.fairytail.directtest.WifiToggleHelper
import org.fairytail.directtest.base.BaseBoundSalutActivity
import org.fairytail.directtest.databinding.ActivityTeacherTestBinding
import org.fairytail.directtest.models.Test
import org.parceler.Parcels

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
enum class State { INITIAL, AWAIT_STUDENTS, AWAIT_RESULTS, SHOW_RESULTS }

class TeacherTestActivity : BaseBoundSalutActivity<ActivityTeacherTestBinding>(R.layout.activity_teacher_test, true) {
    private var state: State = State.INITIAL

    lateinit var test: Test

    override val deviceName = Prefs.teacherInfo.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        test = Parcels.unwrap(intent.getParcelableExtra(EXTRA_TEST))
        moveToState(State.AWAIT_STUDENTS)
    }

    fun moveToState(state: State, intent: Intent? = null) {
        if (this.state == state) return

        supportFragmentManager.beginTransaction()
                .replace(R.id.placeholder, when(state) {
                    State.AWAIT_STUDENTS -> AwaitStudentsFragment()
                    State.AWAIT_RESULTS -> TODO()
                    State.SHOW_RESULTS -> TODO()
                    else -> throw IllegalStateException()
                })
                .commit()
        this.state = state
    }

    override fun onDataReceived(p0: Any?) {
        TODO("not implemented")
    }

    companion object {
        private val EXTRA_TEST = "EXTRA_TEST"

        fun start(ctx: Context, test: Test) {
            WifiToggleHelper.toggle()
                    .subscribe {
                        ctx.startActivity(Intent(ctx, TeacherTestActivity::class.java).putExtra(EXTRA_TEST, Parcels.wrap(test)))
                    }
        }
    }
}