package com.example.myapp_april_2022

class LoginContract {

    interface View {
        fun setSuccess()
        fun setError()
        fun showProgress()
        fun hideProgress()
        fun setRegistrationSuccess()
        fun chekLogin()
        fun backLogin()
        fun sendPassword()

    }

    interface Presenter {

        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onRegistration(login: String, password: String)
        fun onRemindPassword()

    }
}