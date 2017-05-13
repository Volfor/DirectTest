package org.fairytail.directtest.screens.teacher.testlist

import android.databinding.ObservableArrayList
import android.os.Bundle
import android.view.View
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import kotlinx.android.synthetic.main.activity_test_list.*
import org.fairytail.directtest.BR
import org.fairytail.directtest.R
import org.fairytail.directtest.base.BaseBoundActivity
import org.fairytail.directtest.databinding.ActivityTestListBinding
import org.fairytail.directtest.databinding.ItemTestBinding
import org.fairytail.directtest.models.RealmTest
import org.fairytail.directtest.models.Test
import org.jetbrains.anko.toast

/**
 * Created by Valentine on 5/13/2017.
 */
class TestListActivityViewModel {
    fun onFabCreateTestClick(v: View?) {
        v?.context?.toast("fab clicked")
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
                .type { _, _ ->
                    Type<ItemTestBinding>(R.layout.item_test)
                            .onClick { toast("Clicked #${it.adapterPosition}: ${it.binding.item}") }
                }
                .into(list_test)
    }
}