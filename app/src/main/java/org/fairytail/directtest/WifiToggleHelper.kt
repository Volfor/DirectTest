package org.fairytail.directtest

import android.content.IntentFilter
import android.net.wifi.WifiManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.wifiManager
import rx2.receiver.android.RxReceiver
import java.util.concurrent.TimeUnit

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
object WifiToggleHelper {
    fun toggle(): Observable<Any> {
        return Observable.defer {
            with(App.ctx) {
                if (!wifiManager.isWifiEnabled) {
                    wifiState()
                            .filter { it == WifiManager.WIFI_STATE_ENABLED }
                            .take(1)
                            .map { Any() }
                            .delay(2, TimeUnit.SECONDS)
                            .timeout(10, TimeUnit.SECONDS)
                            .doOnSubscribe { wifiManager.isWifiEnabled = true }
                } else {
                    wifiState()
                            .filter { it == WifiManager.WIFI_STATE_DISABLED }
                            .take(1)
                            .map { Any() }
                            .delay(2, TimeUnit.SECONDS)
                            .timeout(10, TimeUnit.SECONDS)
                            .flatMap { toggle() }
                            .doOnSubscribe { wifiManager.isWifiEnabled = false }
                }
            }
        }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun wifiState(): Observable<Int> {
        return RxReceiver.receives(App.ctx, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
                .map { it.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1) }
    }
}