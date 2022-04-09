package com.example.myapp_april_2022.ui.login

import android.os.Handler
import android.os.Looper
import com.example.myapp_april_2022.domain.LoginApi
import com.example.myapp_april_2022.data.MockLoginApiImpl


class Presenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val myHandler = Handler(Looper.getMainLooper())
    private var api: LoginApi = MockLoginApiImpl()


    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }


    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        myHandler.post {
            view?.hideProgress()
            if ((api.login(login, password))) {
                view?.setSuccess()
            } else {
                view?.setError()
            }
        }

    }


    override fun onRegistration(login: String, password: String) {

            myHandler.post {
                view?.hideProgress()
                if (api.login(login, password)) {
                    view?.chekLogin()
                } else if (api.registration(login,password, mail = "")) {
                    view?.setError()
                } else {
                    view?.setRegistrationSuccess()
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

}