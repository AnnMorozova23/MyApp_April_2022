package com.example.myapp_april_2022

import android.os.Handler
import android.os.Looper
import java.lang.Thread.sleep


class Presenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private var validLogin: String = "morozova"
    private var validPassword: String = "ann"
    private val myHandler = Handler(Looper.getMainLooper())

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }


    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(2_000)
            myHandler.post {
                view?.hideProgress()
                if (checkCredentials(login, password)) {
                    view?.setSuccess()

                } else {
                    view?.setError()
                }
            }

        }.start()

    }

    override fun onCredentials(login: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun onRegistration() {
        TODO("Not yet implemented")
    }

    override fun onRemindPassword() {
        TODO("Not yet implemented")
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        return (login == validLogin && password == validPassword)
    }
}