package org.fairytail.directtest.screens.teacher.await_students

import android.content.Context
import android.content.Intent
import android.databinding.ObservableArrayList
import android.os.Bundle
import com.peak.salut.SalutDevice
import org.fairytail.directtest.Prefs
import org.fairytail.directtest.R
import org.fairytail.directtest.WifiToggleHelper
import org.fairytail.directtest.base.BaseBoundSalutActivity
import org.fairytail.directtest.databinding.ActivityTeacherTestBinding
import org.fairytail.directtest.models.Test
import org.parceler.Parcels

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
class TeacherTestActivity : BaseBoundSalutActivity<ActivityTeacherTestBinding>(R.layout.activity_teacher_test) {
    private val devices = ObservableArrayList<SalutDevice>()

    lateinit var test: Test

    override val deviceName = Prefs.teacherInfo.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        test = Parcels.unwrap(intent.getParcelableExtra(EXTRA_TEST))

//        network.startNetworkService { salutDevice ->
//            devices.add(salutDevice)
//        }
//
//        LastAdapter(devices, BR.item)
//                .type { _, _ -> Type<ItemDeviceBinding>(R.layout.item_device) }
//                .into(recycler)
    }

    override fun onDataReceived(p0: Any?) {
        TODO("not implemented")
    }

    companion object {
        private val EXTRA_TEST = "EXTRA_TEST"

        fun start(ctx: Context, test: Test) {
            WifiToggleHelper.toggle()
                    .subscribe {
                        ctx.startActivity(Intent(ctx, TeacherTestActivity::class.java).putExtra(EXTRA_TEST, Parcels.wrap(test)))
                    }
        }
    }
}