package org.fairytail.directtest.screens.student.test

import android.databinding.ObservableArrayList
import android.os.Bundle
import android.view.View
import com.github.ajalt.timberkt.e
import com.github.ajalt.timberkt.i
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.peak.salut.SalutDevice
import kotlinx.android.synthetic.main.fragment_select_teacher.*
import org.fairytail.directtest.BR
import org.fairytail.directtest.R
import org.fairytail.directtest.databinding.FragmentSelectTeacherBinding
import org.fairytail.directtest.databinding.ItemDeviceBinding
import org.fairytail.directtest.screens.student.BaseBoundStudentTestFragment

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
class SelectTeacherFragment : BaseBoundStudentTestFragment<FragmentSelectTeacherBinding>(R.layout.fragment_select_teacher) {
    private val devices = ObservableArrayList<SalutDevice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        network.discoverNetworkServices(
                { device ->
                    waitingLayout.visibility = View.GONE
                    devices.add(device)
                }, true)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LastAdapter(devices, BR.item)
                .type { _, _ -> Type<ItemDeviceBinding>(R.layout.item_device)
                        .onClick {
                            network.registerWithHost(it.binding.item,
                                                     {
                                                         activity.moveToState(State.AWAIT_START)
                                                         i { "Failed to register!" }
                                                     },
                                                     {
                                                         e { "Failed to register!" }
                                                     }
                            )
                        }
                }
                .into(recycler)
    }
}