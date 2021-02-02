package com.example.mygithubclient

import android.app.Application
import com.example.mygithubclient.mvp.di.AppComponent
import com.example.mygithubclient.mvp.di.DaggerAppComponent
import com.example.mygithubclient.mvp.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }


}