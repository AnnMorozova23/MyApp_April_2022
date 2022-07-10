package com.example.myapp_april_2022

import junit.framework.Assert.*
import com.example.myapp_april_2022.domain.LoginApi
import com.example.myapp_april_2022.domain.LoginUseCase
import com.example.myapp_april_2022.ui.login.LoginContract
import com.example.myapp_april_2022.ui.login.Presenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class PresenterTest {
    private lateinit var presenter: Presenter


    @Mock
    private lateinit var api: LoginApi


    @Mock
    private lateinit var loginUseCase: LoginUseCase

    @Mock
    private lateinit var loginContract: LoginContract.View


    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        presenter = Presenter(api, loginUseCase)

    }

    @Test
    fun onAttachTest() {

        assertNotNull(presenter.onAttach(loginContract))

    }


    @Test
    fun onLoginTest() {
        val login = "some login"
        val password = "some password"
        presenter.onLogin(login, password)
        Mockito.verify(loginUseCase, Mockito.times(1))
    }

    @Test
    fun onLoginTestErrorScenario() {
        val login = ""
        val password = ""

        loginUseCase.login(login, password) {
            loginContract.setError()

            Mockito.verify(loginContract, Mockito.times(1)).setError()
        }

    }

    @Test
    fun onForgotPassword() {
        val login = "somelogin@"

        presenter.onForgotPassword(login)

        Mockito.verify(api, Mockito.times(1)).forgotPassword(login)
    }

}





