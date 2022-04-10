package com.example.myapp_april_2022.ui.login

import android.os.Handler
import android.os.Looper
import com.example.myapp_april_2022.domain.LoginApi
import com.example.myapp_april_2022.domain.LoginUseCase
import com.example.myapp_april_2022.utils.Publisher


class LoginViewModel(private val api: LoginApi, private val loginUseCase: LoginUseCase) :
    LoginContract.ViewModel {

    private val myHandler = Handler(Looper.getMainLooper())

    override val shouldShowProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val errorText: Publisher<String?> = Publisher()
    override val isRegistration: Publisher<Boolean> = Publisher()
    override val isSendPassword: Publisher<String> = Publisher()


    override fun onLogin(login: String, password: String) {

        shouldShowProgress.post(true)
        loginUseCase.login(login, password) { result ->
            if (result) {

                shouldShowProgress.post(false)
                isSuccess.post(true)
                errorText.post("")
            } else {
                shouldShowProgress.post(false)
                errorText.post("Неверный логин или пароль, попробуйете еще раз")
            }
        }

    }


    override fun onRegistration(login: String, password: String) {

        myHandler.post {

            shouldShowProgress.post(false)
            when {
                api.login(login, password) -> {
                    errorText.post("Учетная запись уже создана")
                }
                api.registration(login, password, mail = "") -> {
                    errorText.post("Неверный логин или пароль, попробуйете еще раз")
                }
                else -> {
                    isRegistration.post(true)
                }
            }
        }
    }

    override fun onForgotPassword(login: String) {
        shouldShowProgress.post(true)
        api.forgotPassword(login)
        myHandler.post {
            shouldShowProgress.post(false)
            isSendPassword.post("Новый пароль выслан на вашу электронную почту")
        }
    }

    override fun onLogOut(): Boolean {
        api.logout()
        return true
    }

}