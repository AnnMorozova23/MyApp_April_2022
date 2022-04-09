package com.example.myapp_april_2022

import android.app.Application
import android.content.Context
import com.example.myapp_april_2022.data.MockLoginApiImpl
import com.example.myapp_april_2022.domain.LoginApi

class App : Application() {

    val api: LoginApi by lazy { MockLoginApiImpl() }
}

val Context.app: App
    get() {
        return applicationContext as App
    }
