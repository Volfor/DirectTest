package org.fairytail.directtest

import android.view.View
import org.fairytail.directtest.base.BaseBoundActivity
import org.fairytail.directtest.databinding.ActivityMainBinding
import org.fairytail.directtest.screens.student.AwaitActivity
import org.fairytail.directtest.screens.teacher.testlist.TestListActivity
import org.jetbrains.anko.startActivity

/**
 * Created by Valentine on 5/13/2017.
 */
class MainActivityViewModel {
    fun onTeacherClick(v: View?) {
        v?.context?.startActivity<TestListActivity>()
    }

    fun onStudentClick(v: View?) {
        //TODO: go to wait screen
        v?.context?.startActivity<AwaitActivity>()
    }
}

class MainActivity : BaseBoundActivity<ActivityMainBinding>(R.layout.activity_main) {

}