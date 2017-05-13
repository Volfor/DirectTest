package org.fairytail.directtest.screens.teacher

import android.databinding.ViewDataBinding
import android.os.Bundle
import com.peak.salut.Salut
import org.fairytail.directtest.base.BaseBoundFragment
import org.fairytail.directtest.models.Test
import org.fairytail.directtest.screens.teacher.test.TeacherTestActivity

/**
 * Created by Valentine on 5/13/2017.
 */
abstract class BaseBoundTeacherTestFragment<out T : ViewDataBinding>(
        layoutId: Int
) : BaseBoundFragment<T>(layoutId) {
    lateinit var activity: TeacherTestActivity
    lateinit var test: Test
    lateinit var network: Salut

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity = getActivity() as TeacherTestActivity
        test = activity.test
        network = activity.network
    }
}
