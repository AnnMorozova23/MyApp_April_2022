package com.example.myapp_april_2022.ui.login

import android.os.Handler
import android.os.Looper
import com.example.myapp_april_2022.domain.LoginApi
import com.example.myapp_april_2022.domain.LoginUseCase


class Presenter(private val api: LoginApi, private val loginUseCase: LoginUseCase ) : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val myHandler = Handler(Looper.getMainLooper())



    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }


    override fun onLogin(login: String, password: String) {
        view?.showProgress()

        loginUseCase.login(login,password){ result->
            view?.hideProgress()
            if (result) {
                view?.setSuccess()
            } else {
                view?.setError()
            }

        }


    }


    override fun onRegistration(login: String, password: String) {

            myHandler.post {
                view?.hideProgress()
                when {
                    api.login(login, password) -> {
                        view?.chekLogin()
                    }
                    api.registration(login,password, mail = "") -> {
                        view?.setError()
                    }
                    else -> {
                        view?.setRegistrationSuccess()
                    }
                }
            }
    }

    override fun onForgotPassword(login: String) {
        view?.showProgress()
        api.forgotPassword(login)
        myHandler.post {
            view?.hideProgress()
            view?.sendPassword()
        }
    }

    override fun onLogOut(): Boolean {
        api.logout()
        return true
    }

}