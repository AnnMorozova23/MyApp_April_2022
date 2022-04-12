package com.example.myapp_april_2022.data

import android.os.Handler
import android.os.SystemClock.sleep
import com.example.myapp_april_2022.domain.LoginApi
import com.example.myapp_april_2022.domain.LoginUseCase


class LoginUseCaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : LoginUseCase {
    override fun login(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread {
            sleep(2_000)
            val result = api.login(login, password)
            uiHandler.post {
                callback(result)
            }

        }.start()
    }
}