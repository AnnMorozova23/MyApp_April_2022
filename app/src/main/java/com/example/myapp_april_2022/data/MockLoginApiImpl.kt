package com.example.myapp_april_2022.data

import android.os.SystemClock.sleep
import com.example.myapp_april_2022.domain.LoginApi

class MockLoginApiImpl : LoginApi {

    private val validLogin: String = "morozova"
    private val validPassword: String = "ann"


    override fun login(login: String, password: String): Boolean {
        Thread {
            sleep(2_000)
        }.start()
        return login == validLogin && password == validPassword
    }

    override fun registration(login: String, password: String, mail: String): Boolean {
        Thread {
            sleep(2_000)
        }.start()
        return (login.isEmpty() && password.isEmpty())
    }

    override fun logout(): Boolean {
        Thread {
            sleep(3_000)
        }.start()
        return true
    }

    override fun forgotPassword(login: String): Boolean {
        Thread {
            sleep(3_000)
        }.start()
        return true
    }


}