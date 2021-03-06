package org.fairytail.directtest.screens.student

import android.databinding.ViewDataBinding
import android.os.Bundle
import com.peak.salut.Salut
import org.fairytail.directtest.base.BaseBoundFragment
import org.fairytail.directtest.screens.student.test.StudentTestActivity

/**
 * Created by Valentine on 5/13/2017.
 */
abstract class BaseBoundStudentTestFragment<out T : ViewDataBinding>(
        layoutId: Int
) : BaseBoundFragment<T>(layoutId) {
    lateinit var activity: StudentTestActivity
    lateinit var network: Salut

    val test by lazy { activity.test }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity = getActivity() as StudentTestActivity
        network = activity.network
    }
}
