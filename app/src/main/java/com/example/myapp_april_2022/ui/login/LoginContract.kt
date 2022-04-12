package com.example.myapp_april_2022.ui.login

import com.example.myapp_april_2022.utils.Publisher

class LoginContract {

    /*interface View {
        fun setSuccess()
        fun setError()
        fun showProgress()
        fun hideProgress()
        fun setRegistrationSuccess()
        fun chekLogin()
        fun backLogin()
        fun sendPassword()

    }*/

    interface ViewModel {
        val shouldShowProgress: Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText:Publisher<String?>
        val isRegistration: Publisher<Boolean>
        val isSendPassword: Publisher<String>



        fun onLogin(login: String, password: String)
        fun onRegistration(login: String, password: String)
        fun onForgotPassword(login: String)
        fun onLogOut():Boolean
    }
}