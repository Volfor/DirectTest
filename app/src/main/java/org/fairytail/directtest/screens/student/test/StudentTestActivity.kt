package org.fairytail.directtest.screens.student.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.fairytail.directtest.Prefs
import org.fairytail.directtest.R
import org.fairytail.directtest.WifiToggleHelper
import org.fairytail.directtest.base.BaseBoundSalutActivity
import org.fairytail.directtest.databinding.ActivityStudentTestBinding
import org.fairytail.directtest.models.Test
import org.parceler.Parcels

/**
 * Created by Valentine on 5/13/2017.
 */
class StudentTestActivity : BaseBoundSalutActivity<ActivityStudentTestBinding>(R.layout.activity_student_test) {
    override val deviceName = Prefs.studentInfo.name

//    private val devices = ObservableArrayList<SalutDevice>()

    lateinit var test: Test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        test = Parcels.unwrap(intent.getParcelableExtra(EXTRA_TEST))

//        network.discoverNetworkServices({ device -> devices.add(device) }, true)

//        LastAdapter(devices, BR.item)
//                .type { _, _ -> Type<ItemDeviceBinding>(R.layout.item_device)
//                        .onClick { network.registerWithHost(it.binding.item, { toast("Registered!") }, { toast("Failed to register!") } ) }
//                }
//                .into(recycler)
    }

    override fun onDataReceived(p0: Any?) {

    }

    companion object {
        private val EXTRA_TEST = "EXTRA_TEST"

        fun start(ctx: Context, test: Test) {
            WifiToggleHelper.toggle()
                    .subscribe {
                        ctx.startActivity(Intent(ctx, StudentTestActivity::class.java).putExtra(EXTRA_TEST, Parcels.wrap(test)))
                    }
        }
    }
}