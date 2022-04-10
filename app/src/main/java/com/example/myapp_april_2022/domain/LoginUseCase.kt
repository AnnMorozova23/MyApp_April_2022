package com.example.myapp_april_2022.domain

interface LoginUseCase {

    fun login(login: String, password: String, callback: (Boolean) -> Unit)
}