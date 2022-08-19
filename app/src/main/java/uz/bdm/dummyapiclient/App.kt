package uz.bdm.dummyapiclient

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import uz.bdm.dummyapiclient.database.AppDatabase

class App : MultiDexApplication() {

    companion object {
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        MultiDex.install(this)
        AppDatabase.initDatabase(this)
    }
}