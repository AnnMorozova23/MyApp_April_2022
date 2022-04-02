package com.example.myapp_april_2022

class LoginContract {

    interface View {
        fun setSuccess()
        fun setError()
        fun showProgress()
        fun hideProgress()

    }

    interface Presenter {

        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentials(login: String, password: String)
        fun onRegistration()
        fun onRemindPassword()

    }
}