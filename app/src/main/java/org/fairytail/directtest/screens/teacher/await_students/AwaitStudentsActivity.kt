//package org.fairytail.directtest.screens.teacher.await_students
//
//import android.content.Context
//import android.content.Intent
//import android.databinding.ObservableArrayList
//import android.os.Bundle
//import com.github.nitrico.lastadapter.BR
//import com.github.nitrico.lastadapter.LastAdapter
//import com.github.nitrico.lastadapter.Type
//import com.peak.salut.Salut
//import com.peak.salut.SalutDataReceiver
//import com.peak.salut.SalutDevice
//import com.peak.salut.SalutServiceData
//import kotlinx.android.synthetic.main.activity_await_students.*
//import org.fairytail.directtest.R
//import org.fairytail.directtest.WifiToggleHelper
//import org.fairytail.directtest.base.BaseBoundActivity
//import org.fairytail.directtest.databinding.ActivityAwaitStudentsBinding
//import org.fairytail.directtest.databinding.ItemDeviceBinding
//import org.fairytail.directtest.models.Test
//import org.jetbrains.anko.toast
//import org.parceler.Parcels
//import java.util.*
//
///**
// * Created by Alex on 5/13/2017.
// * GitHub: https://github.com/s0nerik
// * LinkedIn: https://linkedin.com/in/sonerik
// */
//class AwaitStudentsActivity : BaseBoundActivity<ActivityAwaitStudentsBinding>(R.layout.activity_teacher_test) {
//    private val devices = ObservableArrayList<SalutDevice>()
//
//    private lateinit var test: Test
//
//    lateinit var network: Salut
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        test = Parcels.unwrap(intent.getParcelableExtra(EXTRA_TEST))
//
//        val dataReceiver = SalutDataReceiver(this, {  })
//        val serviceData = SalutServiceData("directtest", 50489, "Teacher ${Random().nextInt(999999)}")
//        network = Salut(dataReceiver, serviceData, { toast("Device is not supported!") })
//
//        network.startNetworkService { salutDevice ->
//            devices.add(salutDevice)
//        }
//
//        LastAdapter(devices, BR.item)
//                .type { _, _ -> Type<ItemDeviceBinding>(R.layout.item_device) }
//                .into(recycler)
//    }
//
//    companion object {
//        private val EXTRA_TEST = "EXTRA_TEST"
//
//        fun start(ctx: Context, test: Test) {
//            WifiToggleHelper.toggle()
//                    .subscribe {
//                        ctx.startActivity(Intent(ctx, AwaitStudentsActivity::class.java).putExtra(EXTRA_TEST, Parcels.wrap(test)))
//                    }
//        }
//    }
//}