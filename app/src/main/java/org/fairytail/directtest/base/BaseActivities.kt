package org.fairytail.directtest.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.bluelinelabs.logansquare.LoganSquare
import com.peak.salut.Salut
import com.peak.salut.SalutDataReceiver
import com.peak.salut.SalutServiceData
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.realm.Realm
import org.fairytail.directtest.models.Message
import org.jetbrains.anko.toast

/**
 * Created by Valentine on 5/13/2017.
 */
abstract class BaseActivity : RxAppCompatActivity() {
    protected open val layoutId: Int?
        get() = null

    protected val realm: Realm by lazy { Realm.getDefaultInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutId != null) setContentView(layoutId!!)
    }

    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }
}

abstract class BaseBoundActivity<out T : ViewDataBinding>(
        protected val layoutId: Int,
        protected val disableTransitions: Boolean = true
) : RxAppCompatActivity() {
    private lateinit var innerBinding: T
    protected val binding: T by lazy { innerBinding }

    protected val realm: Realm by lazy { Realm.getDefaultInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (disableTransitions) overridePendingTransition(0, 0)
        innerBinding = DataBindingUtil.setContentView(this, layoutId)
    }

    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }
}

abstract class BaseBoundSalutActivity<out T : ViewDataBinding>(layoutId: Int, val host: Boolean, disableTransitions: Boolean = true
) : BaseBoundActivity<T>(layoutId, disableTransitions) {
    protected abstract val deviceName: String

    lateinit var network: Salut

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        network = Salut(
                SalutDataReceiver(this, { onMessageReceived(LoganSquare.parse(it as String, Message::class.java)) }),
                SalutServiceData("test_direct", 1337, deviceName)
        ) { toast("Sorry, but this device does not support WiFi Direct.") }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (host) network.stopNetworkService(false)
        else network.unregisterClient(false)
    }

    abstract fun onMessageReceived(msg: Message)
}