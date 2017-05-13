package org.fairytail.directtest.screens.student

import android.databinding.ViewDataBinding
import android.os.Bundle
import org.fairytail.directtest.base.BaseBoundFragment

/**
 * Created by Valentine on 5/13/2017.
 */
abstract class BaseBoundStudentTestFragment<out T : ViewDataBinding>(
        layoutId: Int
) : BaseBoundFragment<T>(layoutId) {
//    lateinit  var activity: StudentTestActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        activity = getActivity() as StudentTestActivity
    }
}
