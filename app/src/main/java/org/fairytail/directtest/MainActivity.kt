package org.fairytail.directtest

import android.os.Bundle
import android.view.View
import org.fairytail.directtest.base.BaseBoundActivity
import org.fairytail.directtest.databinding.ActivityMainBinding
import org.fairytail.directtest.screens.student.test.StudentTestActivity
import org.fairytail.directtest.screens.teacher.testlist.TestListActivity

/**
 * Created by Valentine on 5/13/2017.
 */
class MainActivityViewModel {
    fun onTeacherClick(v: View) {
        TestListActivity.start(v.context)
    }

    fun onStudentClick(v: View) {
        StudentTestActivity.start(v.context)
    }
}

class MainActivity : BaseBoundActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = MainActivityViewModel()
    }
}