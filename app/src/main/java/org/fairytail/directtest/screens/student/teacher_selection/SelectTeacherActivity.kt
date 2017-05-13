package org.fairytail.directtest.screens.student.teacher_selection

import android.content.Context
import android.content.Intent
import android.databinding.ObservableArrayList
import android.os.Bundle
import com.github.nitrico.lastadapter.BR
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.peak.salut.Salut
import com.peak.salut.SalutDataReceiver
import com.peak.salut.SalutDevice
import com.peak.salut.SalutServiceData
import kotlinx.android.synthetic.main.activity_select_teacher.*
import org.fairytail.directtest.R
import org.fairytail.directtest.WifiToggleHelper
import org.fairytail.directtest.base.BaseBoundActivity
import org.fairytail.directtest.databinding.ActivitySelectTeacherBinding
import org.fairytail.directtest.databinding.ItemDeviceBinding
import org.jetbrains.anko.toast
import java.util.*

/**
 * Created by Valentine on 5/13/2017.
 */
class SelectTeacherActivity : BaseBoundActivity<ActivitySelectTeacherBinding>(R.layout.activity_select_teacher) {
    private val devices = ObservableArrayList<SalutDevice>()

    lateinit var network: Salut

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataReceiver = SalutDataReceiver(this, {  })
        val serviceData = SalutServiceData("directtest", 50489, "Student ${Random().nextInt(999999)}")
        network = Salut(dataReceiver, serviceData, { toast("Device is not supported!") })

        network.discoverNetworkServices({ device -> devices.add(device) }, true)

        LastAdapter(devices, BR.item)
                .type { _, _ -> Type<ItemDeviceBinding>(R.layout.item_device)
                        .onClick { network.registerWithHost(it.binding.item, { toast("Registered!") }, { toast("Failed to register!") } ) }
                }
                .into(recycler)
    }

    companion object {
        fun start(ctx: Context) {
            WifiToggleHelper.toggle()
                    .subscribe {
                        ctx.startActivity(Intent(ctx, SelectTeacherActivity::class.java))
                    }
        }
    }
}