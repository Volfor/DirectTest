package org.fairytail.directtest

import android.app.Application
import android.content.Context
import com.bluelinelabs.logansquare.LoganSquare
import com.bluelinelabs.logansquare.typeconverters.EnumValueTypeConverter
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration
import org.fairytail.directtest.models.MessageType
import rx_activity_result2.RxActivityResult


/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */
open class App : Application() {
    companion object {
        private lateinit var context: Context

        val ctx: Context
            get() = context
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        RxActivityResult.register(this)

        Realm.init(this)
        Realm.setDefaultConfiguration(
                RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build()
        )

        initLoganSquareTypes()
    }

    private fun initLoganSquareTypes() {
        LoganSquare.registerTypeConverter(MessageType::class.java, EnumValueTypeConverter(MessageType::class.java))
    }
}

class DebugApp : App() {
    override fun onCreate() {
        super.onCreate()
        initStetho()
        FakeDataProvider.provideFakeTests()
    }

    private fun initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build()
        )
    }
}