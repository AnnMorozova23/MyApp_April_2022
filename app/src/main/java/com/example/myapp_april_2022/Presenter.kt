package com.example.myapp_april_2022

import android.os.Handler
import android.os.Looper
import java.lang.Thread.sleep


class Presenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val validLogin: String = "morozova"
    private val validPassword: String = "ann"
    private val notValidLogin: String = ""
    private val notValidPassword: String = ""
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


    override fun onRegistration(login: String, password: String) {

        Thread {
            sleep(1_000)
            myHandler.post {
                view?.hideProgress()
                if (checkCredentials(login, password)) {
                    view?.chekLogin()
                } else if (login == notValidLogin || password == notValidPassword) {
                    view?.setError()
                } else {
                    view?.setRegistrationSuccess()
                }
            }
        }.start()
    }

    override fun onRemindPassword() {

        view?.showProgress()
        Thread {
            sleep(2_000)
            myHandler.post {
                view?.hideProgress()
                view?.sendPassword()
            }
        }.start()
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        return (login == validLogin && password == validPassword)
    }


}