package com.example.myapp_april_2022

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.myapp_april_2022.data.LoginUseCaseImpl
import com.example.myapp_april_2022.data.MockLoginApiImpl
import com.example.myapp_april_2022.domain.LoginApi
import com.example.myapp_april_2022.domain.LoginUseCase

class App : Application() {

    val api: LoginApi by lazy { MockLoginApiImpl() }
    val useCase: LoginUseCase by lazy {
        LoginUseCaseImpl(app.api, Handler(Looper.getMainLooper()))
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }
