package org.fairytail.directtest.screens.teacher.test

import android.databinding.ObservableArrayList
import android.os.Bundle
import android.view.View
import com.github.nitrico.lastadapter.BR
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.peak.salut.SalutDevice
import kotlinx.android.synthetic.main.fragment_await_students.*
import org.fairytail.directtest.R
import org.fairytail.directtest.databinding.FragmentAwaitStudentsBinding
import org.fairytail.directtest.databinding.ItemDeviceBinding
import org.fairytail.directtest.screens.teacher.BaseBoundTeacherTestFragment

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
class AwaitStudentsFragment : BaseBoundTeacherTestFragment<FragmentAwaitStudentsBinding>(R.layout.fragment_await_students) {
    private val devices = ObservableArrayList<SalutDevice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        network.startNetworkService { salutDevice -> devices.add(salutDevice)}
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LastAdapter(devices, BR.item)
                .type { _, _ -> Type<ItemDeviceBinding>(R.layout.item_device) }
                .into(recycler)
    }
}