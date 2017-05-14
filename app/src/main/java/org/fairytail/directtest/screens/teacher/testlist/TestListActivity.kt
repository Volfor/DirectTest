package org.fairytail.directtest.screens.teacher.testlist

import android.content.Context
import android.content.Intent
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.view.View
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import kotlinx.android.synthetic.main.activity_test_list.*
import org.fairytail.directtest.BR
import org.fairytail.directtest.R
import org.fairytail.directtest.RecyclerDivider
import org.fairytail.directtest.base.BaseBoundActivity
import org.fairytail.directtest.databinding.ActivityTestListBinding
import org.fairytail.directtest.databinding.ItemTestBinding
import org.fairytail.directtest.models.RealmTest
import org.fairytail.directtest.models.Test
import org.fairytail.directtest.screens.teacher.test.TeacherTestActivity

/**
 * Created by Valentine on 5/13/2017.
 */
class TestListActivityViewModel {
    fun onFabCreateTestClick(v: View?) {
//        v?.context?.toast("fab clicked")
    }
}

class TestListActivity : BaseBoundActivity<ActivityTestListBinding>(R.layout.activity_test_list) {
    val testList = ObservableArrayList<Test>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        realm.where(RealmTest::class.java)
                .findAllAsync()
                .addChangeListener { it ->
                    testList.clear()
                    testList.addAll(it.map { Test(it) })
                }

        LastAdapter(testList, BR.item)
                .type { _, position ->
                    Type<ItemTestBinding>(R.layout.item_test)
                            .onClick { TeacherTestActivity.start(this, it.binding.item) }
                            // TODO: for showcase only! Remove after showcasing!
                            .onBind {
                                when (position) {
                                    0 -> { it.binding.testIcon.setImageResource(R.drawable.oop) }
                                    1 -> { it.binding.testIcon.setImageResource(R.drawable.programming) }
                                    2 -> { it.binding.testIcon.setImageResource(R.drawable.cplusplus) }
                                }
                            }
                }
                .into(list_test)

        list_test.addItemDecoration(RecyclerDivider.horizontal)
    }

    companion object {
        fun start(ctx: Context) {
            ctx.startActivity(Intent(ctx, TestListActivity::class.java))
        }
    }
}