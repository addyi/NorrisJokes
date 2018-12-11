package de.eosn.norrisjokes

import android.app.Application
import de.eosn.norrisjokes.module.appModule
import de.eosn.norrisjokes.module.remoteDataSourceModule
import org.koin.android.ext.android.startKoin

class NorrisApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin(this, listOf(appModule, remoteDataSourceModule), loadPropertiesFromFile = true)
    }
}